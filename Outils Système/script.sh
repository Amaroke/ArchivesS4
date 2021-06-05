#!/usr/bin/env bash

# Question commande
# Etape 1 : chmod +rx script.sh
# Etape 2 : ./script.sh [fichier] [options]
# Les options sont détaillées dans la fonction usage accessible avec -h

# Question usage
function usage() {
  echo "Commande d'utilisation du script : ./script.sh [fichier] [options]"
  echo "[fichier] correspond au fichier de configuration à utiliser."
  echo "-h : Help, sert à afficher l'aide."
  echo "-v [taille] : Définir une taille de verbosité (1 et 2 pris en charge)."
}

# Question argument(s)
config_file="clean.conf"
if [ -e "$1" ]; then
  config_file="$1"
  shift
else
  echo "Vous n'avez pas fournis de fichier de configuration !"
  usage
  exit 2
fi

# Question option(s)
verbosity=1
while getopts "hv:" arg; do
  case "${arg}" in
  h)
    usage
    exit 0
    ;;
  v)
    verbosity=$OPTARG
    ;;
  *)
    echo "Argument invalide !"
    usage
    exit 1
    ;;
  esac
done

# Question regex
if [[ $verbosity =~ [0-9]+ ]]; then
  echo "verbosity est bien un nombre, et il est réglé sur : $verbosity"
else
  echo "verbosity n'est pas un nombre !"
  exit 1
fi

# Question config
to_clean=".aux .log .fls .toc .cb2 .synctex.gz .gls .brf .bbl .bcf"
[[ $verbosity -ge 2 ]] && echo "Les fichiers portants ces extensions vont êtres supprimés :"
[[ $verbosity -ge 1 ]] && echo "TO_CLEAN=${to_clean}"

# Question nettoyage
for f in *; do
  ext=${f##*.}
  regex="${ext}"
  if [[ $to_clean =~ $regex ]]; then
    [[ $verbosity -ge 2 ]] && echo "Le fichier ${f} va être supprimé."
    [[ $verbosity -ge 1 ]] && echo "Appel de : rm ${f}"
    rm $f
  fi
done

echo "Nettoyage terminé !"

exit 0

# Verificar si la base de datos 'mysql' existe
user="root"
password="123456"
database="iaew"

if ! mysql --host=localhost --user="$user" --password="$password" --database="$database" -e "SELECT 1" 2>/dev/null; then
  echo "MySQL is unavailable"
  exit 1
fi

# Verificar si la tabla 'docker_ready' existe
if ! mysql --host=localhost --user="$user" --password="$password" --database="$database" \
   -e "SELECT COUNT(*) FROM information_schema.tables WHERE table_schema = '$database' AND table_name = 'docker_ready'" | grep -q "1"; then
  echo "La tabla 'docker_ready' no existe"
  exit 1
fi

# Verificar si la tabla 'docker_ready' está poblada
if ! mysql --host=localhost --user="$user" --password="$password" --database="$database" \
   -e "SELECT COUNT(*) FROM docker_ready" | grep -q "1"; then
  echo "La tabla 'docker_ready' no está poblada"
  exit 1
fi
echo "Finalizo la inicialización de la base de datos"
exit 0

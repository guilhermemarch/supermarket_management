Start-Sleep -Seconds 10

docker exec supermarket-postgres psql -U developer -d projetocarrinho -f /docker-entrypoint-initdb.d/init.sql
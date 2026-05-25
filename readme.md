If the container has psql available, run:

`docker exec -it <container_name_or_id> psql -U <username> -d <database_name>`

Useful commands:

`\l      -- list databases`

`\c shppr -- connect to your database`

`\dt     -- list tables`

`\d order_items   -- show table structure`

`SELECT * FROM products;`  


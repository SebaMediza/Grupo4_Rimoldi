import mysql from 'mysql2';  // usa mysql2 con promesas

const connection = mysql.createConnection({
  host: 'localhost',
  user: 'root',
  password: '',
  database: 'rimoldi'
});

export default connection;
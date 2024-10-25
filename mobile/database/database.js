import mysql from 'mysql2';  // usa mysql2 con promesas

/* const connection = createConnection({
    host: 'localhost',
    user: 'root',
    password: '',
    database: 'rimoldi'
});

connection.connect((err) => {
    if (err) {
        console.error('Error connecting to the database:', err.stack);
        return;
    }
    console.log('Connected to the database as id ' + connection.threadId);
}); */

const connection = await mysql.createConnection({
  host: 'localhost',
  user: 'root',
  password: '',
  database: 'rimoldi'
});

/* connection.connect((err) => {
  if (err) {
      console.error('Error connecting to the database:', err.stack);
      return;
  }
  console.log('Connected to the database as id ' + connection.threadId);
}); */

export default connection;
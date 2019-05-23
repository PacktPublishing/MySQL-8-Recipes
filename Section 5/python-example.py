#!/usr/bin/python

# Packt MySQL 8 Recipes

import MySQLdb

# Create connection object
con = MySQLdb.connect(host='127.0.0.1',
    user='hr_user', passwd='secretpassword', db='hr')

# Get a new cursor from the connection object
cur = con.cursor()

print('Employees')
# SELECT statement
sql = 'SELECT first_name, last_name FROM employees WHERE employee_id > %(emp_id)s'
cur.execute(sql, {'emp_id': 200})
# Fetch result
for row in cur.fetchall():
    print(row)

# INSERT statement
sql = ('INSERT INTO departments (department_id, department_name, manager_id, location_id)'
        'VALUES (%s, %s, %s, %s)')
data = (300, 'Database Department', 100, 1700)
cur.execute(sql, data);

print('Departments')
sql = 'SELECT department_name FROM departments WHERE location_id = 1700'
cur.execute(sql)
for row in cur.fetchall():
    print(row)

if con:
    con.close()

from flask import Flask, render_template, flash, redirect, url_for, session, request, logging
#from data import agents
from flask_mysqldb import MySQL
from wtforms import Form, StringField, SelectField, TextAreaField, PasswordField, validators
from passlib.hash import sha256_crypt
from functools import wraps
#from flask_paginate import Pagination, get_page_args

app = Flask(__name__)

# Config MySQL
app.config['MYSQL_HOST'] = 'localhost'
app.config['MYSQL_USER'] = 'engineersticity'
app.config['MYSQL_PASSWORD'] = '@@Eck9833'
app.config['MYSQL_DB'] = 'm-register'
app.config['MYSQL_CURSORCLASS'] = 'DictCursor'
# init MYSQL
mysql = MySQL(app)


# Index
@app.route('/')
def index():
    return render_template('home.html')


# About
@app.route('/about')
def about():
    return render_template('about.html')


# Register Form Class
class RegisterForm(Form):
    title = SelectField(u'Title/ Position', choices=[('distributor', 'Safaricom Distributor'), ('asm', 'Area Sales Manager (ASM)'), ('tdr', 'TDR')])
    name = StringField('Name', [validators.Length(min=1, max=50)])
    username = StringField('Username', [validators.Length(min=4, max=25)])
    email = StringField('Email', [validators.Length(min=3, max=50)])
    password = PasswordField('Password', [
        validators.DataRequired(),
        validators.EqualTo('confirm', message='Passwords do not match')
    ])
    confirm = PasswordField('Confirm Password')
    safaricom_id = StringField('Safaricom ID',[validators.Length(min=2,max=5)])


# User Register
@app.route('/register', methods=['GET', 'POST'])
def register():
    form = RegisterForm(request.form)
    if request.method == 'POST' and form.validate():
        title = form.title.data
        name = form.name.data
        email = form.email.data
        username = form.username.data
        password = sha256_crypt.encrypt(str(form.password.data))
        safaricom_id = form.safaricom_id.data

        if title == 'distributor':                

            # Create cursor
            cur = mysql.connection.cursor()

            #Check whether the user's Safaricom ID exists in the DB
            result = cur.execute("SELECT * FROM user_saf_distr WHERE safaricom_id = %s", [safaricom_id])
            #print(result)

            if result > 0:                
                data = cur.fetchone()
                safaricom_id = data['safaricom_id']

                #Delete the existing entry
                cur.execute("DELETE FROM user_saf_distr WHERE safaricom_id = %s", [safaricom_id])

                # Check whether the username is taken
                result3 = cur.execute("SELECT * FROM users WHERE username = %s", [username])

                if result3 == 0:
                    #Insert into Safaricom Distributor Database
                    result2 = cur.execute("INSERT INTO user_saf_distr(name, email, username, password, safaricom_id, title) VALUES(%s, %s, %s, %s, %s, %s)", (name, email, username, password,safaricom_id,title))

                    if result2:
                        #Inserting to the users DB after all the verifications have been made
                        cur.execute("INSERT INTO users(name, email, username, password, safaricom_id, title) VALUES(%s, %s, %s, %s, %s, %s)", (name, email, username, password, safaricom_id, title))
                        # Commit to DB
                        mysql.connection.commit()
                        # Close connection
                        cur.close()

                        flash('You are now registered as a Safaricom Distributor. Log in to continue', 'success')
                        
                        return redirect(url_for('login'))

                    else:
                        flash('Registration failed. Please try again', 'danger')
                        return redirect(url_for('register'))
                else:
                    flash('Username taken. Please try another one!', 'danger')
                    return redirect(url_for('register'))

            else:
                flash('Could not find a Safaricom Distributor with such an ID. Please register with the Safaricom given ID', 'danger')
                return redirect(url_for('register'))

            return redirect(url_for('register'))

        elif title == 'asm':
             # Create cursor
            cur = mysql.connection.cursor()

            #Check whether the user's Safaricom ID exists in the DB
            result = cur.execute("SELECT * FROM user_asm WHERE safaricom_id = %s", [safaricom_id])

            if result > 0:                
                data = cur.fetchone()
                safaricom_id = data['safaricom_id']

                #Delete the existing entry
                cur.execute("DELETE FROM user_asm WHERE safaricom_id = %s", [safaricom_id])
                # Execute query
                result3 = cur.execute("SELECT * FROM users WHERE username = %s", [username])

                if result3 == 0:
                    #Insert into the ASM Database
                    result2 = cur.execute("INSERT INTO user_asm(name, email, username, password, safaricom_id, title) VALUES(%s, %s, %s, %s, %s, %s)", (name, email, username, password,safaricom_id, title))

                    if result2:
                        #Inserting to the users DB after all the verifications have been made
                        cur.execute("INSERT INTO users(name, email, username, password, safaricom_id, title) VALUES(%s, %s, %s, %s, %s,%s)", (name, email, username, password,safaricom_id, title))
                        # Commit to DB
                        mysql.connection.commit()
                        # Close connection
                        cur.close()

                        flash('You are now registered as a Safaricom Area Sales manager. Log in to continue', 'success')
                        
                        return redirect(url_for('login'))

                    else:
                        flash('Registration failed. Please try again', 'danger')
                        return redirect(url_for('register'))
                else:
                    flash('Username taken. Please try another one!', 'danger')
                    return redirect(url_for('register'))


            else:
                flash('Could not find a Safaricom Area Sales Manager with such an ID. Please try again', 'danger')
                return redirect(url_for('register'))

            return redirect(url_for('register'))

        else:
             # Create cursor
            cur = mysql.connection.cursor()

            #Check whether the user's Safaricom ID exists in the DB
            result = cur.execute("SELECT * FROM user_tdr WHERE safaricom_id = %s", [safaricom_id])

            if result > 0:                
                data = cur.fetchone()
                safaricom_id = data['safaricom_id']

                #Delete the existing entry
                cur.execute("DELETE FROM user_tdr WHERE safaricom_id = %s", [safaricom_id])
                # Execute query
                result3 = cur.execute("SELECT * FROM users WHERE username = %s", [username])

                if result3 == 0:
                    #Insert into the TDR Database
                    result2 = cur.execute("INSERT INTO user_tdr(name, email, username, password, safaricom_id, title) VALUES(%s, %s, %s, %s, %s, %s)", (name, email, username, password,safaricom_id, title))

                    if result2:
                        #Inserting to the users DB after all the verifications have been made
                        cur.execute("INSERT INTO users(name, email, username, password, safaricom_id, title) VALUES(%s, %s, %s, %s, %s,%s)", (name, email, username, password,safaricom_id, title))
                        # Commit to DB
                        mysql.connection.commit()
                        # Close connection
                        cur.close()

                        flash('You are now registered as a Safaricom TDR. Log in to continue', 'success')
                        
                        return redirect(url_for('login'))

                    else:
                        flash('Registration failed. Please try again', 'danger')
                        return redirect(url_for('register'))
                else:
                    flash('Username taken. Please try another one!', 'danger')
                    return redirect(url_for('register'))


            else:
                flash('Could not find a Safaricom TDR with such an ID. Please try again', 'danger')
                return redirect(url_for('register'))

            return redirect(url_for('register'))

    return render_template('register.html', form=form)


@app.route('/login', methods=['GET', 'POST'])
def login():
    if request.method == 'POST':
        # Get Form Fields
        username = request.form['username']
        password_candidate = request.form['password']

        # Create cursor
        cur = mysql.connection.cursor()

        # Get user by username
        result = cur.execute("SELECT * FROM users WHERE username = %s", [username])

        if result > 0:
            # Get stored hash
            data = cur.fetchone()
            password = data['password']

            # Compare Passwords
            if sha256_crypt.verify(password_candidate, password):
                # Passed
                session['logged_in'] = True
                session['username'] = username

                flash('You are now logged in', 'success')
                return redirect(url_for('dashboard'))
            else:
                error = 'Invalid login'
                return render_template('login.html', error=error)
            # Close connection
            cur.close()
        else:
            error = 'Username not found'
            return render_template('login.html', error=error)

    return render_template('login.html')

# Check if user logged in
def is_logged_in(f):
    @wraps(f)
    def wrap(*args, **kwargs):
        if 'logged_in' in session:
            return f(*args, **kwargs)
        else:
            flash('Unauthorized, Please login', 'danger')
            return redirect(url_for('login'))
    return wrap

# Logout
@app.route('/logout')
@is_logged_in
def logout():
    session.clear()
    flash('You are now logged out', 'success')
    return redirect(url_for('login'))

# agents
@app.route('/agents')
@is_logged_in
def agents():
    # Create cursor
    cur = mysql.connection.cursor()

 

        # Get agents
    result = cur.execute("SELECT * FROM agent_data")

    agents = cur.fetchall()
            

    if result > 0:
        return render_template('agents.html', agents=agents)
    else:
        msg = 'No Registered agents Found'
        return render_template('agents.html', msg=msg)
       
    # Close connection
    cur.close()


#Single agent
@app.route('/agent/<string:id>/')
@is_logged_in
def agent(id):
    # Create cursor
    cur = mysql.connection.cursor()

    # Get agent
    result = cur.execute("SELECT * FROM agent_data WHERE id = %s", [id])

    agent = cur.fetchone()

    return render_template('agent.html', agent=agent)

# Dashboard
@app.route('/dashboard')
@is_logged_in
def dashboard():
    # Create cursor
    cur = mysql.connection.cursor()

    # result12 = cur.execute("SELECT * FROM user_asm WHERE =%s", [session['username']] )

    # if result12 > 0:
    #     msg = "Welcome!!"
    #     return render_template('dashboard.html', msg=msg)

    # Get agent data
    result = cur.execute("SELECT * FROM agent_data WHERE tdr_name=%s", [session['username']] )

    agents = cur.fetchall()

    if result > 0:
        return render_template('dashboard.html', agents=agents)
    else:
        msg = 'No Registered Agents Found'
        return render_template('dashboard.html', msg=msg)
    # Close connection
    cur.close()

# agent Form Class
class agentForm(Form):
    name = StringField('Agent Name', [validators.Length(min=1, max=200)])
    id_no = StringField('ID Number', [validators.Length(min=1, max=200)])
    region = SelectField(u'Region', choices=[('0', '----------'), ('coast', 'COAST'), ('great_western', 'GREAT WESTERN'), ('greater_western', 'GREATER WESTERN'), ('kakamega', 'KAKAMEGA'), ('laikipia', 'LAIKIPIA'), ('mt_kenya', 'MT KENYA'), ('nairobi_upper', 'NAIROBI UPPER'), ('nairobi_east', 'NAIROBI EAST'), ('nairobi_west', 'NAIROBI WEST'), ('tsavo', 'TSAVO'),])
    area = StringField('Sales Area', [validators.Length(min=1, max=200)])
    agent_no = StringField('Agent Number', [validators.Length(min=1, max=200)])
    mpesa_name = StringField('M-Pesa Name', [validators.Length(min=1, max=200)])
    phone_no = StringField('Phone Number', [validators.Length(min=1, max=200)])
    imei = StringField('Phone IMEI', [validators.Length(min=1, max=200)])
    town = StringField('Town', [validators.Length(min=1, max=200)])

# Add agent
@app.route('/add_agent', methods=['GET', 'POST'])
@is_logged_in
def add_agent():
    form = agentForm(request.form)
    if request.method == 'POST' and form.validate():
        name = form.name.data
        id_no = form.id_no.data
        area = form.area.data
        agent_no = form.agent_no.data
        mpesa_name = form.mpesa_name.data
        phone_no = form.phone_no.data
        imei = form.imei.data
        town = form.town.data
        region = form.region.data

        # Create Cursor
        cur = mysql.connection.cursor()

        #Execute
        result = cur.execute("SELECT * FROM huawei_phones WHERE IMEI=%s",[imei])

        if result > 0:

            # Execute
            cur.execute("INSERT INTO agent_data(name, id_no, area, agent_no, mpesa_name, phone_no, imei, town, region, tdr_name) VALUES(%s, %s, %s, %s, %s, %s, %s, %s, %s, %s)",(name, id_no, area, agent_no, mpesa_name, phone_no, imei, town, region, session['username']))

            # Commit to DB
            mysql.connection.commit()

            #Close connection
            cur.close()

            flash('Agent Added', 'success')

            return redirect(url_for('dashboard'))
        
        else:
            flash('HUAWEI IMEI NUMBER NOT FOUND!! Try again with a valid IMEI Number', 'danger')
            return render_template('add_agent.html', form=form)

    return render_template('add_agent.html', form=form)


# Edit agent
@app.route('/edit_agent/<string:id>', methods=['GET', 'POST'])
@is_logged_in
def edit_agent(id):
    # Create cursor
    cur = mysql.connection.cursor()

    # Get agent by id
    result = cur.execute("SELECT * FROM agent_data WHERE id = %s", [id])

    agent = cur.fetchone()
    cur.close()
    # Get form
    form = agentForm(request.form)

    # Populate agent form fields
    form.name.data = agent['name']
    form.id_no.data = agent['id_no']     
    form.area.data = agent['area']
    form.agent_no.data = agent['agent_no']
    form.mpesa_name.data = agent['mpesa_name']
    form.phone_no.data = agent['phone_no']
    form.imei.data = agent['imei']
    form.town.data = agent['town']
    form.region.data = agent['region']

    if request.method == 'POST' and form.validate():
        name = request.form['name']
        id_no = request.form['id_no']
        area = request.form['area']
        agent_no = request.form['agent_no']
        mpesa_name = request.form['mpesa_name']
        phone_no = request.form['phone_no']
        imei = request.form['imei']
        town = request.form['town']
        region = request.form['region']

        # Create Cursor
        cur = mysql.connection.cursor()
        app.logger.info(name)
        # Execute
        cur.execute ("UPDATE agent_data SET name=%s, id_no=%s, area=%s,agent_no=%s,mpesa_name=%s,phone_no=%s,imei=%s,town=%s,region=%s WHERE id=%s",(name, id_no, area, agent_no, mpesa_name,phone_no, imei, town, region, id))
        # Commit to DB
        mysql.connection.commit()

        #Close connection
        cur.close()

        flash('Agent Details Updated', 'success')
        
        return redirect(url_for('dashboard'))

    return render_template('edit_agent.html', form=form)

# Delete agent
@app.route('/delete_agent/<string:id>', methods=['POST'])
@is_logged_in
def delete_agent(id):
    # Create cursor
    cur = mysql.connection.cursor()

    # Execute
    cur.execute("DELETE FROM agent_data WHERE id = %s", [id])

    # Commit to DB
    mysql.connection.commit()

    #Close connection
    cur.close()

    flash('Agent Deleted', 'success')

    return redirect(url_for('dashboard'))

@app.route('/search', methods=['GET', 'POST'])
def search():
    if request.method == "POST":
        #Get form data
        search = request.form['search']
        # Create cursor
        cur = mysql.connection.cursor()
        #Execute
        results = cur.execute("SELECT * FROM agent_data WHERE agent_no=%s", [search])
        if results == 0:
            #msg = 'No records with' + search + 'found!!'
            flash('Agent Number "' + search + '" NOT FOUND!!', 'danger')
            return render_template("search.html")
        else:
            records = cur.fetchall()
            return render_template("search.html", records=records)

        # Close connection
        cur.close()
        
    return render_template('search.html')

if __name__ == '__main__':
    app.secret_key='secret123'
    app.run(debug=True, host='0.0.0.0', port=5005)

from flask import Flask, request, jsonify

app = Flask(__name__)

@app.route('/webview/login', methods=['POST'])
def login():
    username = request.form.get('username')
    password = request.form.get('password')
    # Here you would typically check the credentials against a database
    return jsonify({"message": "Login successful", "username": username})

@app.route('/webview/register', methods=['POST'])
def register():
    username = request.form.get('username')
    password = request.form.get('password')
    # Here you would typically save the user to a database
    return jsonify({"message": "Registration successful", "username": username})

if __name__ == '__main__':
    app.run(debug=True, port=5000)

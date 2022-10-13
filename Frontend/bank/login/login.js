const loginUrl = 'http://localhost:8081/user/login';

async function login() {
    let userEmail = document.getElementById("email").value;
    let userPassword = document.getElementById("password").value;
    var responsefromAPI;
    var formdata = new FormData();
    formdata.append("email", userEmail);
    formdata.append("password", userPassword);

    var requestOptions = {
        method: POSTmethodType,
        body: formdata,
        redirect: 'follow'
    };

    const res = await fetch(loginUrl, requestOptions)
    responsefromAPI = await res.json();

    const obj = JSON.parse(JSON.stringify(responsefromAPI));
    if (obj.code == '0') {
        // window.alert("Login Successful");
        localStorage.setItem('user', JSON.stringify(obj.payload));
        window.location.replace(base_url + "/bank/dashboard.html");
    } else {
        window.alert('Invalid Credentials. New user please continue to sign up before login.');
    }
}

function logout() {
    window.localStorage.removeItem('user');
    window.location.replace(base_url + "/bank/login.html");
}

function register() {
    window.location.replace(base_url + "/bank/register.html");
}

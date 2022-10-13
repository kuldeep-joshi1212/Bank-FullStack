const userRegistrationURL = "http://localhost:8081/user/register";

async function register() {
    let userEmail = document.getElementById("email").value;
    let userPassword = document.getElementById("password").value;
    let userName = document.getElementById("userName").value;

    var userData = JSON.stringify({
        "userEmail": userEmail,
        "userName": userName,
        "userPassword": userPassword
    });

    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    var requestOptions = {
        method: POSTmethodType,
        body: userData,
        headers: myHeaders,
        redirect: 'follow'
    };

    const registrationRes = await fetch(userRegistrationURL, requestOptions);
    var responsefromregistrationAPI = await registrationRes.json();

    const obj = JSON.parse(JSON.stringify(responsefromregistrationAPI));
    window.alert(obj.payload);
    window.location.replace(base_url + "/bank/login.html");
}
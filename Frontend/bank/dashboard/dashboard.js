const transactionUrl = 'http://localhost:8081/transaction/balance/';
var user = localStorage.user;

async function initialLoadDashboard() {
    if (typeof user != "undefined") {
        var balance = localStorage.accountBalance;
        if (typeof balance != "undefined") {
            window.localStorage.removeItem('accountBalance');
        }
        var UserObj = JSON.parse(user);
        var greetingDiv = document.getElementById('greetUser');
        let greetingText = '<h1>Hello, ' + UserObj.userName + '!!</h1>';
        greetingDiv.innerHTML += greetingText;

        let userId = UserObj.id;

        var requestOptions = {
            method: GETmethodType,
            redirect: 'follow'
        };
        const accountSummaryRes = await fetch(transactionUrl + userId, requestOptions)
        accountSummaryResponsefromAPI = await accountSummaryRes.json();
        const accountSummary = JSON.parse(JSON.stringify(accountSummaryResponsefromAPI)).payload;

        var userAccountBalanceDiv = document.getElementById('userAccountBalance');
        let userAccountBalanceText = '<p> Balance Summary : Rs. ' + accountSummary.accountBalance + '</p>';
        userAccountBalanceDiv.innerHTML += userAccountBalanceText;
        localStorage.setItem('accountBalance', accountSummary.accountBalance);
    } else {
        window.alert('Please login to continue using wallet.');
        window.location.replace(base_url + "/bank/login.html");
    }
}

async function credit() {
    let amount = document.getElementById("depositAmount").value;
    if (amount < 0) {
        window.alert('Deposit Amount cannot be less than 0!')
        location.reload();
    } else {
        var formdata = new FormData();
        formdata.append("amount", amount);

        var requestOptions = {
            method: POSTmethodType,
            body: formdata,
            redirect: 'follow'
        };

        const res = await fetch(transactionUrl + creditAmount + '/' + JSON.parse(user).id, requestOptions)
        var responsefromtransactionAPI = await res.json();

        const obj = JSON.parse(JSON.stringify(responsefromtransactionAPI));
        window.alert(obj.message);
        location.reload();
    }
}

async function debit() {
    let amount = document.getElementById("withdrawAmount").value;
    if (amount < 0) {
        window.alert('Withdrawal Amount cannot be less than 0!')
        location.reload();
    } else if (amount > localStorage.accountBalance) {
        window.alert('Withdrawal Amount cannot be greater than the account balance!')
        location.reload();
    } else {
        var formdata = new FormData();
        formdata.append("amount", amount);

        var requestOptions = {
            method: POSTmethodType,
            body: formdata,
            redirect: 'follow'
        };

        const res = await fetch(transactionUrl + debitAmount + '/' + JSON.parse(user).id, requestOptions)
        var responsefromtransactionAPI = await res.json();

        const obj = JSON.parse(JSON.stringify(responsefromtransactionAPI));
        console.log(obj);
        window.alert(obj.message);
        location.reload();
    }

}

function openTransactionHistory() {
    window.location.replace(base_url + "/bank/transactionHistory.html");
}
const transactionHistoryUrl = 'http://localhost:8081/transaction/balance/history/';
var user = localStorage.user;

async function initialLoadTransactionHistory() {
    if (typeof user != "undefined") {

        var UserObj = JSON.parse(user);
        let userId = UserObj.id;

        var UserObj = JSON.parse(user);
        var greetingDiv = document.getElementById('greetUser');
        let greetingText = '<h2>Hello, ' + UserObj.userName + '!!</h2>';
        greetingDiv.innerHTML += greetingText;

        var balance = localStorage.accountBalance;
        var totalBalanceDiv = document.getElementById('totalBalance');
        let totalBalanceText = '<h3>Your Current Balance : Rs. ' + balance + '</h3>';
        totalBalanceDiv.innerHTML += totalBalanceText;

        var requestOptions = {
            method: GETmethodType,
            redirect: 'follow'
        };
        const transactionHistoryRes = await fetch(transactionHistoryUrl + userId, requestOptions)
        transactionHistoryResponsefromAPI = await transactionHistoryRes.json();
        const accountSummary = JSON.parse(JSON.stringify(transactionHistoryResponsefromAPI)).payload;
        if (accountSummary.length < 1) {
            var notxnhstDiv = document.getElementById('notxnhst');
            let notxnhstText = '<h1>No Transaction History Available!!</h1>';
            notxnhstDiv.innerHTML += notxnhstText;
        } else {

            var txnhsttableDiv = document.getElementById('txnhsttable');
            let txnhsttableText = '<tr style="text-align: center;"><td><b>Id</b></td><td><b>Transaction Type</b></td><td><b>Amount</b></td><td><b>Date</b></td></tr>';
            txnhsttableDiv.innerHTML += txnhsttableText;
            for (let i = 0; i < accountSummary.length; i++) {
                txnhsttableText = '<trstyle="text-align: center;"><td>' + (i + 1) + '</td><td>' + accountSummary[i].transactionType + '</td><td>' + accountSummary[i].transactionAmount + '</td><td>' + accountSummary[i].created + '</td></tr>';
                txnhsttableDiv.innerHTML += txnhsttableText;
            }
        }
    } else {
        window.alert('Please login to continue using wallet.');
        window.location.replace(base_url + "/bank/login.html");
    }
}
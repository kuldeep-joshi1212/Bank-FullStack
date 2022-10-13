var base_url = window.location.origin;

const POSTmethodType = 'POST';
const GETmethodType = 'GET';

const creditAmount = 'CREDIT';
const debitAmount = 'DEBIT';


function goBackToDashboard() {
    window.location.replace(base_url + "/bank/dashboard.html");
}
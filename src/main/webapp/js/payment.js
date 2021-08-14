window.addEventListener("load", function (event) {
    var url_string = window.location.href;
    var url = new URL(url_string);
    window.history.replaceState({}, document.title, "controller?command=to-payment-form");
});

var paymentInputs;

var paymentPatterns;

window.addEventListener("load", function (event) {

    const holderRegex = new RegExp(document.getElementById('regex-holder').textContent);

    const mmRegex = new RegExp(document.getElementById('regex-mm').textContent);

    const yyRegex = new RegExp(document.getElementById('regex-yy').textContent);

    const numberRegex = new RegExp(document.getElementById('regex-number').textContent);

    const cvcRegex = new RegExp(document.getElementById('regex-cvc').textContent);

    paymentInputs = document.querySelectorAll('.form-control');

    paymentPatterns = {
        holder: holderRegex,
        mm: mmRegex,
        yy: yyRegex,
        number: numberRegex,
        cvc: cvcRegex
    };

    paymentInputs.forEach((input) => {
        input.addEventListener('keyup', (e) => {
            validate(e.target, paymentPatterns[e.target.attributes.id.value]);
        });
    });

});

function validatePaymentForm() {
    var date = new Date();
    var currentYearFirstTwoDigits = date.getFullYear().toString().substr(0, 2);
    var inputMm = document.getElementById('mm').value;
    var inputYy = document.getElementById('yy').value;
    var inputDate = new Date(currentYearFirstTwoDigits.toString() + inputYy.toString(), inputMm.toString());
    if (inputDate < date) {
        document.getElementById('mm').className = 'form-control invalid';
        document.getElementById('yy').className = 'form-control invalid';
    }
    return validateInputs(paymentInputs, paymentPatterns) && (inputDate > date);
}


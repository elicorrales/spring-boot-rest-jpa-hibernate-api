'use strict';
const orderNumberInputElem = document.getElementById('orderNumberInput');
const descriptionInputElem = document.getElementById('descriptionInput');
const dateOrderedInputElem = document.getElementById('dateOrderedInput');
const statusInputElem = document.getElementById('statusInput');

const onSubmitNewCustomerOrderBtnClickDoSubmitNewCustomerOrder = (event) => {
    event.preventDefault();
    app.alerts.hideMessage();
    let number = orderNumberInputElem.value;
    let description = descriptionInputElem.value;
    let dateOrdered = dateOrderedInputElem.value;
    let status = statusInputElem.value;
    axios.post('/customer/'+idOfCustomerSelectedFromList+'/order', { number, description, dateOrdered, status })
    .then(
        result => {
            app.alerts.displayMessage('success','New Customer Order' + number + ' ' + dateOrdered + ' added.');
            app.showCustomerOrdersListSection();
        }
    )
    .catch(
        error => {
            app.utils.displayError(error);
        }
    );

}


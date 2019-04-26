'use strict';
const orderNumberInputElem = document.getElementById('orderNumberInput');
const descriptionInputElem = document.getElementById('descriptionInput');
const dateCreatedInputElem = document.getElementById('dateCreatedInput');
const statusInputElem = document.getElementById('statusInput');

const onSubmitNewCustomerOrderBtnClickDoSubmitNewCustomerOrder = (event) => {
    event.preventDefault();
    app.alerts.hideMessage();
    let number = orderNumberInputElem.value;
    let description = descriptionInputElem.value;
    //let dateCreated = dateCreatedInputElem.value; //let server set date
    let status = statusInputElem.value;
    axios.post('/customer/'+idOfCustomerSelectedFromList+'/order', { number, description, status })
    .then(
        result => {
            app.alerts.displayMessage('success','New Customer Order' + number + ' added.');
            app.showCustomerOrdersListSection();
        }
    )
    .catch(
        error => {
            app.utils.displayError(error);
        }
    );

}


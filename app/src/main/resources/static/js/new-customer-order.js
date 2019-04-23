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
    axios.post('/customers/customer/'+idOfCustomerSelectedFromList+'/order', { number, description, dateOrdered, status })
    .then(
        result => {
            app.alerts.displayMessage('success','New Customer Order' + number + ' ' + dateOrdered + ' added.');
            app.showCustomerOrdersListSection();
        }
    )
    .catch(
        error => {
            let message = '';
            if (error.response) {
                if (error.response.data) {
                    if (error.response.data.message) {
                        message = error.response.data.message;
                    } else if (error.response.data.length > 0) {
                        error.response.data.forEach(x => { message += x + '<br/>'});
                    } else {
                        message = error.response.data;
                    }

                } else {
                    message = error.response;
                }
            } else {
                message = error;
            }
            app.alerts.displayMessage('danger',message);
        }
    );

}


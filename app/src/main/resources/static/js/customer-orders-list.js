'use strict';
const customerOrdersListAreaElem = document.getElementById('customerOrdersListArea');

var customerOrdersList = {};


const displayCustomerOrder = (idx, order) => {
    let html = ''
        + '<tr>'
        + '    <td id="custId-' + order.id + '" hidden>' + order.customerId + '</td>'
        + '    <td id="custOrdId-' + order.id + '">' + order.id + '</td>'
        + '    <td id="custOrdNum-' + order.id + '">' + order.number + '</td>'
        + '    <td id="custOrdDesc-' + order.id + '">' + order.description + '</td>'
        + '    <td id="custOrdDate-' + order.id + '">' + order.dateCreated + '</td>'
        + '    <td id="custOrdStat-' + order.id + '">' + order.status + '</td>'
        + '    <td><h5><a href="" onclick="onEditCustomerOrderClickDoEditCustomerOrder(event,'+order.id+')"> <i class="glyphicon glyphicon-pencil"></i> </a> '
        + '    <a href="" onclick="onDeleteCustomerOrderClickDoDeleteCustomerOrder(event,'+order.id+')"> <i class="glyphicon glyphicon-trash"></i> </a> '
        + '</tr>';

    return html;
}

const displayCustomerOrdersList = (orders) => {
    if (orders === undefined || orders.length < 1) {
        app.alerts.displayMessage('warning','No Orders Found');
        return '';
    }
    let html = '';
    orders.forEach( (order,idx) => {
       html += displayCustomerOrder(idx,order); 
    });
    return html;
}

const getCustomerOrdersList = () => {
    axios.get('/order/customer/'+idOfCustomerSelectedFromList)
    .then(
        result => {
            //app.alerts.displayMessage('success','Got Customers..');
            customerOrdersListAreaElem. innerHTML = displayCustomerOrdersList(result.data);
        }
    )
    .catch(
        error => {
            app.utils.displayError(error);
        }
    )
}

customerOrdersList.getCustomerOrdersList = getCustomerOrdersList;
app.customerOrdersList = customerOrdersList;
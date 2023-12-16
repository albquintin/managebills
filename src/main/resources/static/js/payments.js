$(".open-modal").on("click", function(){
  var row = $(this).closest('tr');
  var paymentId = row.find("td:first-child");
  var orderNumber = row.find("td:nth-child(2)");
  var url = "/payments/payments/delete/" + paymentId.text();
  $("#modal-content").append("¿Desea borrar de manera definitiva el pago?");
  $(".delete-button").attr("href", url);
  $(".modal").addClass("is-active");
});
$(".close-modal").on("click", function(){
  $(".modal").removeClass("is-active");
  $("#modal-content").empty();
});
$(document).ready(function()  {
  $('#paymentsTable').DataTable();
});
$('#paymentsTable').DataTable({
    'order': [[0, 'desc']],
    'aoColumnDefs': [{
        'bSortable': false,
        'aTargets': [-1]
    }]
});
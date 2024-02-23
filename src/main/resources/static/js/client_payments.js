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
$("#year").on( "change", function() {
  $('#changeYear').click();
});
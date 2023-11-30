$(document).ready(function()  {
    $('#suppliersTable').DataTable();
});
$('#suppliersTable').DataTable({
    'order': [[3, 'desc']],
    'aoColumnDefs': [{
        'bSortable': false,
        'aTargets': [-1]
    }]
});
function generarPDF(table) {
    const { jsPDF } = window.jspdf;
    const doc = new jsPDF({ orientation: "landscape" });

    let titulo = document.getElementById("title").innerText;

    doc.text(titulo, 14, 10); // Agregar un título

    doc.autoTable({
        html: table,  // Extrae datos de la tabla HTML
        startY: 20,  // Posición de inicio
        margin: { left: 5, right: 5 }
    });

    doc.save("tabla.pdf"); // Descarga el PDF
}

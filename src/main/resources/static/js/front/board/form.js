window.addEventListener("DOMContentLoaded", function() {
    ClassicEditor
        .create( document.querySelector( '#content' ), {
            height: 400,
        } )
        .then( editor => {
            window.editor = editor;
            editor.ui.view.editable.element.style.height = '450px';
        } )
        .catch( err => {
            console.error( err.stack );
        } );
});

function insertEditor(imgUrl) {
    editor.execute( 'insertImage', { source: imgUrl } );
}
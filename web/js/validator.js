$(document).ready(function () {
    $('#categorias').bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            descripcion: {
                validators: {
                    notEmpty: {
                        message: 'La descripcion de la categoria es requerida'
                    },
                    stringLength: {
                        min: 3,
                        message: 'La descripcion debe tener minimo 3 digitos'
                    }
                }
            },
            estado: {
                validators: {
                    notEmpty: {
                        message: 'El estado de la categoria es requerido'
                    }
                }
            }
        },
    });
    function mensaje() {

    }

    $('#publicaciones').bootstrapValidator({
        message: 'Este valor no es valido',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            titulo: {
                validators: {
                    notEmpty: {
                        message: 'El titulo de la publicacion no puede estar vacio'
                    },
                    stringLength: {
                        min: 3,
                        message: 'El titulo no debe ser menor a 3 caracteres'
                    }
                }
            },
            categoria: {
                validators: {
                    notEmpty: {
                        message: 'Debe seleccionar una categoria'
                    }
                }
            },
            contenido: {
                validators: {
                    notEmpty: {
                        message: 'Debe ingresar un contenido a la publicación'
                    }
                }
            },
            estado: {
                validators: {
                    notEmpty: {
                        message: 'Debe asignar un estado a la publicación'
                    },
                    stringLength: {
                        min: 6,
                        max: 6,
                    }
                }
            }
        }
    });
});
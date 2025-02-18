package com.taller.AppEuro.enumeraciones;

public enum CategoriaReparacion {
    REPUESTOS,REPARACIONES, MECANICA, ELECTRICA, CHAPA_PINTURA,
    MANTENIMIENTO;

    @Override
    public String toString() {
        switch (this) {
            case CHAPA_PINTURA:
                return "Chapa y Pintura";
            case MECANICA:
                return "Mecánica";
            case ELECTRICA:
                return "Eléctrica";
                case REPUESTOS:
                    return "Repuestos";
                    case REPARACIONES:
                        return "Reparaciones";
                        case MANTENIMIENTO:
                            return "Mantenimiento";
            default:
                return this.name().charAt(0) + this.name().substring(1).toLowerCase();
        }
    }


    }

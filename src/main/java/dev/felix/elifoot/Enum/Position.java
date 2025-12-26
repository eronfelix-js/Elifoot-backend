package dev.felix.elifoot.Enum;

public enum Position {

    GOALKEEPER("Goleiro"),
    RIGHT_BACK("Lateral Direito"),
    LEFT_BACK("Lateral Esquerdo"),
    CENTER_BACK("Zagueiro"),
    DEFENSIVE_MIDFIELDER("Volante"),
    CENTRAL_MIDFIELDER("Meio-campista Central"),
    ATTACKING_MIDFIELDER("Meia Ofensivo"),
    RIGHT_WINGER("Ponta Direita"),
    LEFT_WINGER("Ponta Esquerda"),
    SECOND_STRIKER("Segundo Atacante"),
    STRIKER("Atacante");

    private final String label;

    Position(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}


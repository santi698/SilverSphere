package cell;
/**
 * 
 * Enumerativo que representa el resultado de alguno de los métodos move de los {@link CellContent}.
 * 
 */
public enum MoveReturnValue { 
	/**
	 * Valor que retorna {@link Character} cuando llega a la celda {@link Target}
	 */
	TARGET_REACHED,
	/**
	 * Valor que retorna {@link Character} cuando cae al agua.
	 */
	WATER_REACHED,
	/**
	 * Valor que retorna {@link Character}, {@link IceBlock} y {@link Box} cuando no pudieron moverse.
	 */
	UNABLE_TO_MOVE,
	/**
	 * Valor que retorna {@link Character}, {@link IceBlock} y {@link Box} cuando pudieron moverse.
	 * {@link Character} lo retorna solo cuando no llego al destino y no cayó al agua.
	 */
	MOVED
}


class Const {

	public static readonly DEBUG: boolean = true;
	
	public static readonly TITRE_MAIN: string = 'ID Card';
	
	public static readonly LENGTH = {
		PSEUDO: {
			min: 3,
			max: 32
		},
		MAIL: {
			min: 6,
			max: 64
		},
		MDP: {
			min: 6,
			max: 32
		}
	};
	
	public static readonly TRANSITION_DURATION: number = 500;
	
	private constructor() {}

}
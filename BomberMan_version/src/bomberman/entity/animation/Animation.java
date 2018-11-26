package bomberman.entity.animation;

import bomberman.entity.Entity;


public abstract class Animation extends Entity {

	protected int Animation = 0;
	protected final int MAX_ANIMATION = 7500; 
	
	protected void Animation() {
		if(Animation < MAX_ANIMATION) Animation++; else Animation = 0;
	}

}

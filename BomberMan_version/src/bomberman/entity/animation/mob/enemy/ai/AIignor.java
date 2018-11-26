package bomberman.entity.animation.mob.enemy.ai;

public class AIignor extends AI {

	@Override
	public int calculateDirection() {
		return random.nextInt(4);
	}

}

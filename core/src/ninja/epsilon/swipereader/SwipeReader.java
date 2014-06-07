package ninja.epsilon.swipereader;

public class SwipeReader implements InputReader {

	@Override
	public long input(long curTime) {
		//TODO
		return 0;
	}

	public DirectionGestureDetector getSwipeInputProcessor() {
	   return new DirectionGestureDetector(new DirectionGestureDetector.DirectionListener() {
		   @Override
		   public void onUp() {
    			// TODO Auto-generated method stub
    		}

    		@Override
    		public void onRight() {
    			// TODO Auto-generated method stub

    		}

    		@Override
    		public void onLeft() {
    			// TODO Auto-generated method stub

    		}

    		@Override
    		public void onDown() {
    			// TODO Auto-generated method stub

    		}
    	});
   }
}	

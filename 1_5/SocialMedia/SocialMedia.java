
import java.io.BufferedReader;
import java.io.FileReader;
public class SocialMedia {

	private static final String FILE_NAME = "/home/xiaofanlin/eclipse-workspace/SocialMedia/src/social_connection.txt";
	
	public static void main(String[] args) {
		BufferedReader br = null;
		FileReader fr = null;
		UnionSet members = new UnionSet(10);

		
		try {
			fr = new FileReader(FILE_NAME);
			br = new BufferedReader(fr);
			String date;
			
			
			while((date = br.readLine()) != null && !members.completed()) {
				members.union(Integer.parseInt(date.substring(7,8)), Integer.parseInt(date.substring(9,10)));
				if(members.completed()) {
					System.out.println(date.substring(0,6) + ": all connected");
				}
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(br != null) br.close();
				if(fr != null) fr.close();
				
			}catch (Exception ex) {
				// TODO: handle exception
				ex.printStackTrace();
			}
		}
		
		/**
		for (int i = 0; i <= 9; i++) {
			for (int j = i + 1; j <= 9; j++) {
				System.out.printf("%d, %d: %b \n", i, j, members.connected(i, j));
			}
		}
		**/
	}
	
	
}

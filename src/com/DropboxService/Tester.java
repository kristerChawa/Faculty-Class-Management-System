package com.DropboxService;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DropBoxService db = new DropBoxService();
//		String path = db.uploadFile("Certificate.jpg");
//		System.out.println(path);
		
		db.listFolders().stream().forEach(System.out::println);
		
	}
	
//	public static void Parse(Metadata json){
//		
//		try {
//			JsonFactory factory = new JsonFactory();
//			JsonParser parser = factory.createParser(json);
//			
//			while(!parser.isClosed()){
//				JsonToken jsonToken = parser.nextToken();
//				System.out.println(jsonToken);
//			}
//		} catch (JsonParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}

}

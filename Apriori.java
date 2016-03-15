import java.util.*;
import java.io.*;
/**
 * @author Purvesh
 * Apriori Algorithm implementation 
 * 
 */
public class Apriori {

 public static void main(String[] args)throws IOException  {
	Scanner s = new Scanner(System.in);

	System.out.println("Enter path of your data file: (It should be in C:\\path\\innerpath\\filename format)");
	String path = s.nextLine();

    System.out.println("Enter Minimum Support rate(IN range of 1-10)");
    float min_supp = s.nextFloat();
    if(min_supp <1 || min_supp > 10){
		System.out.println("Invalid Support rate choice");
		System.exit(0);
	}
    System.out.println("Enter Minimum confidence rate(IN range of 0.1-10)");
    float min_conf = s.nextFloat();
    if(min_conf<0.1 || min_conf >10){
		System.out.println("Invalid confidence rate choice");
		System.exit(0);
	}

	String e_path = "C:\\Data\\hashmapoutput88";
	
   
   try {
	   	 
         HashMap <String,Integer> m1 = new LinkedHashMap<String,Integer>();
         HashMap <String,Integer> m2 = new LinkedHashMap<String,Integer>();
         HashMap <String,Integer> q = new LinkedHashMap<String,Integer>();
         ArrayList<String>  a1 = new ArrayList<String>();
         ArrayList <String> a2 = new ArrayList<String>(); 
 
         File fr = new File(path);
         Scanner sc = new Scanner(fr);
         int my_count=0;
         while (sc.hasNext()) {
        	 my_count++;
        	 String words = sc.next();
             String[] space = words.split(" ");
             for (int i = 0; i < space.length; i++) {
                 a1.add(space[i]);
             }
         }
                  
         System.out.println(a1);       
         System.out.println("For Frequent Set 1: ");
         
         // find_word_set_from_data_set() function will generate from Frequent item itemset from candidate item set
         // Parameter of this function are k-1(candidate item set), data path,user specified support, and last is K(i.e k-frequent item itemset) 
         m1 = find_word_set_from_data_set(a1, path, min_supp,1);
         
         //printing frequent itemset (here 1 frequnt item set)
         Set q1 = m1.entrySet();
    	 Iterator itq1 = q1.iterator();
    	 while(itq1.hasNext()) {
    		 Map.Entry mentry = (Map.Entry)itq1.next();
    		 //System.out.print(mentry.getKey()+" -- "+mentry.getValue()+"\n");
    		 String hash_key = (String) mentry.getKey();
    		 int hash_value = (int) mentry.getValue();
    		 hash_key.trim();
    		 q.put(hash_key, hash_value);
    	 }
    	        
         System.out.println("For Frequent Set 2: ");
         
         // candidate_itemset function will generate candidate item set, this function will gennerate upto 2 candidate itemset.
         // it has 2 parameters, 1) previous frequent itemset, and 2)K 
         a2 = candidate_itemset(m1,2);     
         m2 = find_word_set_from_data_set(a2, path, min_supp,2);
         
         Set q2 = m2.entrySet();
    	 Iterator itq2 = q2.iterator();
    	 while(itq2.hasNext()) {
    		 Map.Entry mentry = (Map.Entry)itq2.next();
    		 //System.out.print(mentry.getKey()+" -- "+mentry.getValue()+"\n");
    		 String hash_key = (String) mentry.getKey();
    		 int hash_value = (int) mentry.getValue();
    		 hash_key.trim();
    		 q.put(hash_key, hash_value);
    	 }
         
         Set set = m2.entrySet();
         Iterator iterator = set.iterator();
         while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry)iterator.next();
            System.out.print(mentry.getKey()+" ==== "+mentry.getValue()+"\n");
         }
         System.out.println("\n\n");
         
         
         
         //writetofile(file,m2,2);
         
         // write_result_to_outputfile2 function will store output to external file.
         write_result_to_outputfile2(m2,2,e_path);
         
         HashMap <String,Integer> m = new LinkedHashMap<String,Integer>();
         
         ArrayList<String>        a = new ArrayList<String>();
         
         int count=3;
         
         
         //this loop will automatically generate K-frequent item set.
         while(!m2.isEmpty()){             
        	 a = candidate_itemset_general(m2,(count-1));
    
        	m2.clear();
     
        	 System.out.println("For Iterator set "+count+":");
        	 
        	 m = find_word_set_from_data_set(a, path, min_supp,count);
        	 
        	 

             Set q4 = m.entrySet();
        	 Iterator itq4 = q4.iterator();
        	 while(itq4.hasNext()) {
        		 Map.Entry mentry = (Map.Entry)itq4.next();
        		 //System.out.print(mentry.getKey()+" -- "+mentry.getValue()+"\n");
        		 String hash_keyy = (String) mentry.getKey();
        		 int hash_valuey = (int) mentry.getValue();
        		 hash_keyy.trim();
        		 hash_keyy.replaceAll("\\s+$", "");
        		 //System.out.println("----="+hash_keyy+"----=");
        		 q.put(hash_keyy, hash_valuey);
        	 }
        	 //writetofile(file,m,count);
        	 write_result_to_outputfile2(m,count,e_path);
        	 Set set2 = m.entrySet();
        	 Iterator iterator2 = set2.iterator();
        	 while(iterator2.hasNext()) {
        		 Map.Entry mentry = (Map.Entry)iterator2.next();
        		 System.out.print(mentry.getKey()+" -- "+mentry.getValue()+"\n");
        		 String hash_key = (String) mentry.getKey();
        		 int hash_value = (int) mentry.getValue();
        		 m2.put(hash_key, hash_value);
        	 }
        	 System.out.println("\n\n");    
        	 m.clear();

        	 count++;
         	}
            
         
    	 
    	 HashMap <Integer,String> h88 = new LinkedHashMap<Integer,String>();
         
    	 
    	// for storing subset permutation in external file named P
         File f1 =new File("C:\\Data\\p");
         f1.delete();
	  		if(!f1.exists()){
	  			f1.createNewFile();
	  		}
	  		File f2 =new File("C:\\Data\\p2");
	        f2.delete();
	 		if(!f2.exists()){
	 			f2.createNewFile();
	 		}
	  		
	 	 
	 	//In this file all frequent item set is stored.	
	 	 File file88 = new File("C:\\Data\\hashmapoutput88");

	   	 FileReader ff= new FileReader(file88.getAbsolutePath());
		 BufferedReader bb = new BufferedReader(ff);
		 HashMap <String, String> hr = new LinkedHashMap<String, String>();
			ArrayList<String> my = new ArrayList<String>();

  		
			
			//IN this loop subset of all frequent item set will be generated
			
			for(int cnt=3; cnt<6; cnt++){
             String line;
             
	         String compare = "iterator "+ cnt;
	         System.out.println(cnt+" -- "+compare);
	         while(!(line = bb.readLine()).equals(compare)){
	        	 //System.out.println("line - - "+line);
	        	 
	        	 int length = line.split("\\s+").length;
	        	 if(length>=3){
	        		 HashMap <Integer,String> h = new LinkedHashMap<Integer,String>();
	        		 //System.out.println("compare ----------- "+compare);
	        		 //System.out.println("line ----------- "+line);
	        		 String left[] = line.split("=====");
	        		 String[] input = left[0].trim().split("\\s+");
	        		 
	        		 //this for loop will convert word to int and and this integer is as a input to permutation function 
	        		 
	        		 
	        		 for(int i=0;i<input.length;i++){
	        			 h.put(i, input[i]);
	        		 }
	        		 String permu_input="";
	        		 Set pur = h.entrySet();
	                 Iterator it9 = pur.iterator();
	                 while(it9.hasNext()) {
	                    Map.Entry mentry = (Map.Entry)it9.next();
	                    //System.out.println("keyyyyyy       "+mentry.getKey()+" "+mentry.getValue()+"\n");
	                    permu_input = permu_input+ mentry.getKey();

	                 }
	                
	                 //This function will generate subset/permutation of giiven input.
	         		 permutation(permu_input,f1);
	         		 FileReader ff1= new FileReader(f1.getAbsolutePath());
	        		 BufferedReader bb1 = new BufferedReader(ff1);
	        		 String p_line;
	        		 
	        		 while((p_line = bb1.readLine())!=null){
	            		 //System.out.println(p_line);
	               			String[] d = p_line.split("");
	               			String final_per="";
	               			int f=0;
	               			for(String se : d){
	               				int j = Integer.parseInt(se);
	               				
	               				String b = h.get(j);
	               				my.add(b);
	               				if(f==0){
	               					final_per = b;
	               				} else{
	               					final_per = final_per+" "+b;	
	               				}
	               				
	               				f++;
	               			}
	               			hr.put(final_per, ""); //this hasmap will store all permutation.
	           	 	}
	         		
	         		
	        	 }
   	 
	         }
  		
  		}
  		

  		 HashMap <String,String> hf = new LinkedHashMap<String,String>();
   		FileReader fw = new FileReader(f1.getName());
   		BufferedReader bw = new BufferedReader(fw);
   		String read;
   		
   		System.out.println("Start : -----------------------------");
   		
        Set q24 = q.entrySet();
    	 Iterator itq24 = q24.iterator();
    	 while(itq24.hasNext()) {
    		 Map.Entry mentry = (Map.Entry)itq24.next();
    		 System.out.print(mentry.getKey()+" -- "+mentry.getValue()+"\n");

    	 }

    	 
         Set q8 = q.entrySet();
    	 Iterator itq8 = q8.iterator();
    	 while(itq8.hasNext()) {
    		 Map.Entry mentry = (Map.Entry)itq8.next();
    		 //System.out.print("----="+mentry.getKey()+"--=="+mentry.getValue()+"\n");
    		 //String hash_key = (String) mentry.getKey();
    		 //int hash_value = (int) mentry.getValue();
    		 //q.put(hash_key, hash_value);
    	 }
    	 //System.exit(0);
  		
    	 
 		Set pur2 = hr.entrySet();
        Iterator it92 = pur2.iterator();
        
        System.out.println("\n\n Generated Rules : \n\n");
        
        //this is file where all rules will be stored.
        File fout = new File("D:\\rules");
        if(!fout.exists()){
        	fout.createNewFile();
        }

	   	FileWriter ffout= new FileWriter(fout.getAbsolutePath(),true);
		BufferedWriter bbout = new BufferedWriter(ffout);
        bbout.write("Summary:"+"\n");
        bbout.write("Total rows in original set : "+my_count+"\n");
        bbout.write("The selected Measures : Support="+min_supp+" ," + "Confidence="+ min_conf+"\n");
        bbout.write("------------------------------------------"+"\n");
        bbout.write("Discovered Rules:" + "\n");
        
        int rule_cnt=1;
        
        //this is looop where confidence will be count, pruning would be done and all rules are generated. 
        while(it92.hasNext()) {
           Map.Entry mentry = (Map.Entry)it92.next();
                     
           
           String h_key = (String) mentry.getKey();
           h_key.trim();
           
           String[] tom2 = h_key.split(" ");
           for (String hk : tom2){
        	   
        	   if(q.containsKey(h_key)){
        		   float first_t = q.get(h_key); // getting occurence of word
        	   	   float second_t = q.get(hk);
        	   	   //System.out.println(h_key+" = "+first_t);
        	   	   //System.out.println(hk+" = "+second_t);
        	   	   float confidence_ratio = first_t/second_t;  //calculate confidence rate
        	   	   if(confidence_ratio>=min_conf){               //pruning step 
        	   		   System.out.println("Rule "+rule_cnt+": Confident ratio for "+h_key+" and "+hk+" = "+ confidence_ratio);
        	   		
        	   		   // writing to external file
         	   		   bbout.write("Rule "+rule_cnt+": Confident ratio for "+h_key+" and "+hk+" = "+ confidence_ratio+"\n");
         	   		   
        	   		   rule_cnt++;
        	   	   }
        	   		   
        	   } else{
        		   h_key = h_key+" ";
        		   if(q.containsKey(h_key)){
            		   float first_t = q.get(h_key);
            	   	   float second_t = q.get(hk);
            	   	 
            	   	   float confidence_ratio = first_t/second_t;  //calculate confidence rate
            	   	  if(confidence_ratio>=min_conf){
            	   		   System.out.println("Rule "+rule_cnt+": Confident ratio for "+h_key+" and "+hk+" = "+ confidence_ratio);
            	   		 
            	   		   bbout.write("Rule "+rule_cnt+": Confident ratio for "+h_key+" and "+hk+" = "+ confidence_ratio+"\n");
            	   		   
            	   		   rule_cnt++;
            	   		   
            	   	  }
            	   		  
            	   } else{
            		   continue;
            	   }
        	   }
           }
           
        }
        
        bbout.write("Total rules discovered:"+ rule_cnt+"\n");
	    System.out.println("\n\n ");
 System.out.println("------Finished----");        
  		
   } catch (Exception e) {
	   System.out.println("Error");
   }

 }
 //this will generate permutation/subset
 public static void permutation(String str, File f1) throws IOException { 
		permutation("", str,f1);

	}

	private static void permutation(String prefix, String str, File f1) throws IOException {
	    
		int n = str.length();
	    if (n == 0){
	    	//System.out.println("prefix "+prefix);
	    	FileWriter fw = new FileWriter(f1.getAbsolutePath(),true);
		     BufferedWriter bw = new BufferedWriter(fw);
		     bw.write(prefix+"\n");
		     bw.close();
	    	
	    }
	    else {
	        for (int i = 0; i < n; i++)
	            permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n),f1);
	    }

		
	}

// Write the final output to output file
private static void write_result_to_outputfile2(HashMap<String, Integer> m, int i,String output_path) {
	try {
			File file = new File(output_path);
			if (!file.exists()) {	// create a file if it doesn't exist
				file.createNewFile();
			}
			
			FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("iterator "+ i + "\n");
			
			 Set set88 = m.entrySet();
	         Iterator iterator88 = set88.iterator();
	         while(iterator88.hasNext()) {
	        	
	            Map.Entry mentry88 = (Map.Entry)iterator88.next();
	            //System.out.print(mentry.getKey()+" -- "+mentry.getValue()+"\n");
	            String hash_key = (String) mentry88.getKey();
	            int hash_value = (int) mentry88.getValue();
	            bw.write(hash_key+" ===== "+hash_value+"\n");
				
	         }
	         bw.close();
	         

			System.out.println("Final Result written to output file.. Done!!");

	} catch (IOException e) {
		e.printStackTrace();
	}		
}


// to generate K candidate item set (3 to K)
private static ArrayList<String> candidate_itemset_general(HashMap<String, Integer> m, int itemsetNumber) {
   ArrayList <String> a = new ArrayList<String>(); 
   ArrayList<String> output = new ArrayList<String>();

   Set set = m.entrySet();
         Iterator iterator = set.iterator();
         while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry)iterator.next();
            a.add((String) mentry.getKey());           
         }

       int len = a.size();
       
       for(int k=0; k<(len-1); k++){
        for(int l=k+1; l<len; l++){
         ArrayList <String> a1 = new ArrayList<String>();

         ArrayList <String> intersection = new ArrayList<String>();
         ArrayList <String> first_arraylist = new ArrayList<String>();
         ArrayList <String> second_arraylist = new ArrayList<String>();

         String[] first = a.get(k).split("\\s+");
         String[] second = a.get(l).split("\\s+");
         
         for(String g:first){
          first_arraylist.add(g);
         }
         
         for(String w:second){
          second_arraylist.add(w);
         }
         String flag = "";
         for(int b=0; b<(itemsetNumber-1); b++){
          if(first[b].equals(second[b])){
           flag = "true";
          } else {
           flag = "false";
           break;
          }
         }
         
         int first_length = first_arraylist.size();
         if(flag.equals("true")){
          intersection = intersection(first,second);
          
          // this loop will go itemset generation
          if(intersection.size() == (first_length-1)){
           a1.addAll(first_arraylist);
           a1.removeAll(second_arraylist);
           a1.addAll(second_arraylist); 
           
           String final_string="";
           for(String h:a1){
            final_string = final_string+h+" ";
           }
           output.add(final_string);
           
          } 
         }
         
         
        }
       }

   return output;
  }

//this will get unique intersection 

private static ArrayList<String> intersection(String[] first, String[] second) {
   ArrayList <String> temp_arr = new ArrayList<String>();

         for (String d1:first) {
             for (String d2:second) {
                 if ((d1.equals(d2))) {
                    temp_arr.add(d1);
                 }
             }
         }

  
         
   return temp_arr;
  }

//this will generate 1 and 2 Candidate itemset.
private static ArrayList<String> candidate_itemset(HashMap<String, Integer> m, int itemsetNumber) {
         
   ArrayList <String> a = new ArrayList<String>();  
   ArrayList <String> a1 = new ArrayList<String>();  
    
       for (String key : m.keySet()) {
        String result = key.trim();
        a.add(result);
    }
   
       int len = a.size();
       
       for(int k=0; k<len; k++){
        for(int l=k+1; l<len; l++){
         a1.add(a.get(k)+ " "+a.get(l));
        }
       }

   return a1;
  }

// This will generate 1 to K frequent itemset.
  private static HashMap<String, Integer> find_word_set_from_data_set(ArrayList<String> a, String path, float min_supp, int itemsetNumber) throws IOException {
   
   HashMap<String, Integer> m = new HashMap<String, Integer>();
   
   for(String word_set : a){
          int cnt = 0;

    String current_line;
          BufferedReader br = new BufferedReader(new FileReader(path));
    while ((current_line = br.readLine()) != null) {
     String[] words = word_set.split("\\s+");
     int k=0;
     for(int j=0; j<itemsetNumber; j++){
      if(current_line.contains(words[j])){
       k++;
      }
     }
     
     if(k == itemsetNumber){
      cnt++;
      if(cnt>=min_supp){
       m.put(word_set, cnt);
      }
     }
    }
   }  
   return m;
  }
 
	// Write the final output to output file
	private static void write_result_to_outputfile(String output_text, String output_path) {
		try {
				File file = new File(output_path);

				if (!file.exists()) {	// create a file if it doesn't exist
					file.createNewFile();
				}

				FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(output_text);
				bw.close();

				System.out.println("Final Result written to output file.. Done!!");

		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

}

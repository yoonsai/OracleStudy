package com.sist.main;

import java.io.FileWriter;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.*;
public class FoodMain2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			int s=1;
		    for (int i = 1; i < 35; i++) {
	        	String url="https://www.visitbusan.net/index.do?uc_seqs=&ucMtmUcSeq=&ucMtmItemUcSeq=&file_name=&gugun_nm=&cate2_nm=&ucc1_seq=17&cate1_nm=&ucdpp_seqs=&uct_seqs=&ucum_seqs=&ucl_seq=7&ucl_use_yn=Y&exclude_uc_seq=&place=&title=&subtitl=&hash_tag=&middle_size_rm2=&menuCd=DOM_000000201002000000&list_type=TYPE_SMALL_CARD&order_type=NEW&listCntPerPage2=8&ucum_seq=&ub_seq=&distance=0.0&cate2_month=&favoriteThis=N&myFavoriteUserId=&sel_visit_place=N&user_id=&search_keyword=&num_room=&ulg_seq=&ucc1_use_yn=&ucc2_seq=&ucg_seq=&ucogl_seq=&main_img_ucmf_seq=&main_title=&charger_positn=&charger_nm=&charger_tel=&tripadvisor_id=&lat=&lng=&bundle_cntnts_yn=&use_yn=Y&sort_num=&page_no="+i;
		        //System.out.println(url);
		        
		        Document doc = Jsoup.connect(url).get();
		        Elements link = doc.select("div.hot_item_list2 div.hot-item2 div.info p.tit a");
		        //Elements images=doc.select("div.hot_item_list2 div.hot-item2 div.box a img");
		        
		        for (int j = 0; j < link.size(); j++) {
		            String subLink = "https://www.visitbusan.net" + link.get(j).attr("href");
		            //System.out.println(subLink);
		            Document doc2=Jsoup.connect(subLink).get();
		            Elements images=doc.select("div.hot_item_list2 div.hot-item2 div.actionImg3 a img");
		            String image="https://www.visitbusan.net"+images.get(j).attr("src").substring(0, images.attr("src").lastIndexOf("thumbL"))+"ttiel";
	        	    //System.out.println(image);
		            Elements titles=doc2.select("section#title div.innerwrap h4.tit");
		            String title=titles.text();
		            //System.out.println(title);
		            
		            Elements contents=doc2.select("div.tab_con div.tripvideo div.cont");
		            String content=contents.text();
		           // System.out.println(content);
		            
		            Elements infotitles=doc2.select("div.tab_con div.tripvideo div.cntInfoDetails ul.InfoD-List li p");  
		            String menu="정보없음";
		            String address="정보없음";
		            String call="정보없음";
		            String rest="정보없음";
		            String home="정보없음";
	            	String time="정보없음";
	            	String price="정보없음";
	            	for(int a=0;a<infotitles.size();a++)
	            	{
	            		String ss=infotitles.get(a).text();
	            		if(ss.contains("대표"))
	            		{
	            			Element infodetails=doc2.select("div.tab_con div.tripvideo div.cntInfoDetails ul.InfoD-List li span").get(a);
	            			menu=infodetails.text();
	            		}
	            		if(ss.contains("주소"))
	            		{
	            			Element infodetails=doc2.select("div.tab_con div.tripvideo div.cntInfoDetails ul.InfoD-List li span").get(a);
	            			address=infodetails.text();
	            		}
	            		if(ss.contains("전화번호"))
	            		{
	            			Element infodetails=doc2.select("div.tab_con div.tripvideo div.cntInfoDetails ul.InfoD-List li span").get(a);
	            			call=infodetails.text();
	            		}
	            		if(ss.contains("휴무일"))
	            		{
	            			Element infodetails=doc2.select("div.tab_con div.tripvideo div.cntInfoDetails ul.InfoD-List li span").get(a);
	            			rest=infodetails.text();
	            		}
	            		if(ss.contains("홈페이지"))
	            		{
	            			Element infodetails=doc2.select("div.tab_con div.tripvideo div.cntInfoDetails ul.InfoD-List li span").get(a);
	            			home=infodetails.text();
	            		}
	            		if(ss.contains("시간"))
	            		{
	            			Element infodetails=doc2.select("div.tab_con div.tripvideo div.cntInfoDetails ul.InfoD-List li span").get(a);
	            			time=infodetails.text();
	            		}
	            		
	            		
	            	}

		            
		            
//		            if(infodetails.size()==4)
//		            {
//		            	menu=infodetails.get(0).text();
//			            address=infodetails.get(1).text();
//			            phone=infodetails.get(2).text();
//		            	time=infodetails.get(3).text();
//		            }
//		            else
//		            {
//			            menu=infodetails.get(0).text();
//			            address=infodetails.get(1).text();
//			            phone=infodetails.get(2).text();   
//			            rest=infodetails.get(3).text();
//			            time=infodetails.get(4).text();
//		            }
		            
		            
		            
		            
		            String msg=(s++)+"|"+title+"|"+image+"|"+content+"|"+
		            		menu+"|"+address+"|"+call+"|"+rest+"|"+time+"\r\n";
		            
		            

		            System.out.println(msg);
		            
//		            FileWriter fw=new FileWriter("c:\\java_data\\food.txt", true);
//		            fw.write(msg);
//		            fw.close();
		        }


	        // 차단을 피하기 위해 지연 추가
			        //Thread.sleep(1000); // 1000밀리초 (1초)의 지연
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}

}
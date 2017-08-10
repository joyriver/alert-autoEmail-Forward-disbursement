package com.kbers.alert;

/**
 * <a href="mailto:joyriver7@gmail.com">Jinxi Hong</a> 2017/4/3 16:46
 */
public class Template {   //  String account, String indicator,String indicatorName,String SevenDaysData
    public  String Template0(String keyword1, String keyword2,String infrigement, String ROName,
                             String ROEmail, String StoreManagerName, String StoreName,String ASIN,
                             String title, String AmazonEmail, String SellerEmail, String accountName
                             ) {
        String html= "<br>Dear CS,<br>"+"Thanks for your email and kind help. Please reply this email via the following four steps:  <br>"+
        "1.Please search your seller account inventory and delete every listing related to  the following brands: <br>"+
                keyword1+","+ keyword2+" <br>" +
                "(If your account is European Account, please make sure you have done it from UK, ES, IT, DE, FR five " +
                "country inventories.) <br>"+
        "2. Please copy the following table and paste it in the Google sheet 'Infringement Follow up with CS " +
                "Project' and help to send it regularly if the RO (Right Owner) doesn’t reply it after two days. <br>"+
                infrigement+"<br>"+
        "3. Please copy the email and send it to the following email address as soon as possible: <br>"+
                ROName+" <br>"+
                ROEmail+" <br>"+
        "4. Please reply the Amazon's notification email which sent you this infringement case <br>"+
        "· Say warm thanks to their notification; <br>" +
                "· Tell them you have deleted relating products and contacted the right owner to resolve this case. <br>"+
        "Note: Please replace all the RED XXXX parts carefully and change the font color from red to black, " +
                "then  read the whole email content TWICE to make sure everything is correct before you send it out. <br>"+
        "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ <br><br>"+
                "Email Brand: <br>"+
        "Dear "+ROName+ ",<br>   I’m sorry for an infringement case regarding your products, I have deleted them <br>"+
        "Email Content: <br>"+
        "=================== <br><br>"+
        "Dear "+ROName+", <br>"+
        "I am "+StoreManagerName+" from Amazon Store "+StoreName+" (Store name), and I am writing to you " +
                "regarding an infringement case on Amazon, because Amazon sent an infringement warning to me, " +
                "asking me to contact you to resolve this issue. The related products are: <br>"+
        "ASIN: "+ASIN+" <br>"+
        "Brand: "+title+" <br>"+
       "I just started doing this online business on Amazon. I’m so sorry that I mistakenly listed your products in " +
                "my store, which was due to I don’t have much experience in technology. Now I have realized that my  " +
                "program catch wrong products. I have deleted all the products related to you from my data pool, so " +
                "this kind of issue will never happen again in the future.<br>"+
        "I sincerely apologize for my mistake has got trouble for you. Would you like to accept my sincerely " +
                " apology? Since I have deleted all your products, and this kind of case will never happen again in the future, " +
                " could you help to send an email from <strong><a href=\"#\">"+ROEmail+" </a></strong> to <strong" +
                "><a href=\"#\"> "+AmazonEmail+" </a></strong> and tell them that this " +
                " infringement case has been resolved? Please list the above asin,  my store name <strong" +
                "><a href=\"#\"> “"+StoreName+"” </a></strong>and email <strong><a href=\"#\">" +
                SellerEmail+" </a></strong>  in the email content, and also cc the email to me so that Amazon will know you are " +
                " mentioning me. "+
                "Your help will be very much appreciated.  It will be a disaster for me and my family if it couldn’t be resolved , " +
                "since Amazon would suspend my selling privilege so that I couldn’t do any business again. <br><br>"+
                "Thanks again for your attention and I look forward to your reply soon. <br>"+
        "Sincerely, <br>"+
                StoreManagerName+" <br>"+
                StoreName+" (Store name) <br>"+
                "=================== <br>"+
        "Please forward the email to me if the RO responds you later.  I wish you have a wonderful day! <br>"+
                "Best <br>"+
        "Infringement Withdrawing Team <br>";
        return html;
    }


    public  String Template1(String keyword1, String keyword2,String infrigement, String ROName,
                             String ROEmail, String StoreManagerName, String StoreName,String ASIN,
                             String title, String AmazonEmail, String SellerEmail, String accountName
    ) {
        String html = "<br>Dear CS,<br>"+
                "Thanks for your email and kind help. Please reply this email via the following four steps:<br>"+
                "1. Please search your seller account inventory and delete every listing related to  the following brands: <br>"+
                keyword1+","+ keyword2+" <br>" +
                "(If your account is European Account, please make sure you have done it from UK, ES, IT, DE, FR five "+
                "country inventories.) <br>"+
                "2. Please copy the following table and paste it in the Google sheet\"Infringement Follow up with CS "+
                "Project\" and help to send it regularly if the RO (Right Owner) doesn’t reply it after two days. <br>"+
                infrigement+"<br>"+
                "3. Please copy the email and send it to the following email address as soon as possible:<br>"+
                ROName+" <br>"+
                ROEmail+" <br>"+
                "4. Please reply the Amazon's notification email which sent you this infringement case <br>"+
                "· Say warm thanks to their notification; <br>" +
                "· Tell them you have deleted relating products and contacted the right owner to resolve this case. <br>"+
                "Note: Please replace all the RED XXXX parts carefully and change the font color from red to black, " +
                "then  read the whole email content TWICE to make sure everything is correct before you send it out. <br>"+
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ <br>"+
                "Email Brand: <br>"+
                "Dear  "+ROName+", <br>    I'm so sorry for the infringement, would you please help me to resolve the problem?<br>"+
                "=======================<br><br>"+
                "Dear "+ROName+",<br>"+
                "Hope everything is fine for you and you are doing the best.<br>"+
                "This is "+StoreManagerName+" from Amazon Store "+StoreName+" (Store name), I'm so sorry to bother you. I got a "+
                "notice from Amazon which required me to contact you to resolve an intellectual property right infringement "+
                "case, otherwise, they would suspend my Amazon selling account. It is regarding the following product: <br>"+
                "ASIN: "+ASIN+" <br>"+
                "Brand: "+title+" <br>"+
                "I'm so sorry for infringing your intellectual property rights. I have immediately deleted all the " +
                "related products as soon as I got the Amazon notice. I sincerely apologize for all the inconvenience" +
                " and displeasure it has brought to you. If it's possible, may I be allowed to explain my selling " +
                "practice in detail? Actually my business model is reselling other Amazon sellers’ products. Before " +
                "uploading, I will get permission from authorized seller. Whenever I sell a product, I will order it " +
                "from my authorized seller on Amazon.com and then send it to my customer. But this time, I catch " +
                "wrong products info due to program’s error.<br><br>"+
                "I apologize again for all the inconvenience caused to you. Though I know that nothing I do could really"+
                "make up for what I did wrongly, I just would like to say, \"I am really very very sorry for you!\". " +
                "I will "+
                "remember this and will never list your products again unless I got your permission.<br><br>"+
                "Since my Amazon selling account is the only financial source to maintain my family's living, and I have "+
                "parents and kids need to feed, I cordially ask you to give me a second chance. Otherwise, it would be "+
                "really very difficult for me to find a new job immediately and it would be really very difficult for my family to "+
                "survive. <br><br>"+
                "I guarantee to you that I would never infringe your property rights again in the future. This guarantee could "+
                "serve as a legal proof against me from any related issue happen in the future. <br><br>"+
                "If you feel this issue has already been resolved, by your mercy and kind heart, could you please write a "+
                "notification email from <strong><a href=\"#\">"+ROEmail+" </a></strong> to <strong><a href=\"#\">"+AmazonEmail+" </a></strong> saying that the case has been resolved? "+
                "Could you please mention my selling account name <strong><a href=\"#\">"+StoreName+" </a></strong>, account email  <strong><a href=\"#\">"+SellerEmail+" </a></strong>, and also "+
                "your above listed products in the email content and cc the email to me? <br><br>"+
                "Thank you for your understanding and I look forward to hearing from you soon. <br><br>"+
                "Best Regards,<br><br>"+
                StoreManagerName+" <br>"+
                StoreName+" (Store name) <br><br>"+
                "=================== <br><br>"+
                "Please forward the email to me if the RO responds you later.  I wish you have a wonderful day!<br>"+
                "Best <br><br>"+
                "Infringement Withdrawing Team<br>";


        return html;
    }

    public  String Template2(String keyword1, String keyword2,String infrigement, String ROName,
                             String ROEmail, String StoreManagerName, String StoreName,String ASIN,
                             String title, String AmazonEmail, String SellerEmail, String accountName
    ) {
        String html = "<br>Dear CS,<br>" +
                "Thanks for your email and kind help. Please reply this email via the following four steps:<br><br>"+
                "1. Please search your seller account inventory and delete every listing related to  the following " +
                "brands:<br>"+
                keyword1+","+ keyword2+" <br>" +
                "(If your account is European Account, please make sure you have done it from UK, ES, IT, DE, FR five "+
                "country inventories.) <br><br>"+
                "2. Please copy the following table and paste it in the Google sheet\"Infringement Follow up with CS "+
                "Project\" and help to send it regularly if the RO (Right Owner) doesn’t reply it after two days. <br>"+
                infrigement+"<br><br>"+
                "3. Please copy the email and send it to the following email address as soon as possible: <br>"+
                ROName+" <br>"+
                ROEmail+" <br><br>"+
                "4. Please reply the Amazon's notification email which sent you this infringement case<br>"+
                "· Say warm thanks to their notification; <br>" +
                "· Tell them you have deleted relating products and contacted the right owner to resolve this case. <br><br>"+
                "Note: Please replace all the RED XXXX parts carefully and change the font color from red to black, " +
                "then  read the whole email content TWICE to make sure everything is correct before you send it out. <br>"+
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ <br><br>"+
                "Email Brand: <br>"+
                "Hello"+ ROName+", <br>     I‘m sorry for bothering you, could you please help to resolve a property rights "+
                "infringement case? <br><br>"+
                "======================== <br><br>"+
                "Hello "+ROName+",<br>"+
                "This is "+StoreManagerName+" from Amazon Store "+StoreName+" (Store name). I got a letter from Amazon "+
                "stating that the following products infringe your intellectual property rights:<br>"+
                "ASIN: "+ASIN+" <br>"+
                "Brand: "+title+" <br><br>"+
                "I sincerely apologize for this issue! I have deleted all the items which related to your brand and I" +
                " promise will never list or sell them again in the future. Unless I got your permission for selling " +
                "them. Amazon requires me to resolve this issue with you, otherwise they would suspend my selling " +
                "account.  <br><br>"+
                "It's truly my fault of not being able to filter out your products during the bulk product listing uploading "+
                "process. I should have deeply researched the intellectual property rights of reselling products beforehand. "+
                "Please do believe that I really didn't mean it, neither did I intend to infringe your intellectual property rights. <br><br>"+
                "I just would like to let you know that I have already removed all the related listings of your products from my inventory database. Meanwhile, I guarantee that I will never " +
                "list ro resell your products again without your authorization. Furthermore, before sourcing any new product to my inventory, we will conduct a full " +
                "review and research on the product to see if it will infringe any third party's intellectual property rights. <br><br>"+
                "Mistakes sometimes may happen, but to forgive is divine. If it's possible, may I ask for your forgiveness "+
                "for my mistake? If you are willing to help, could you kindly do me a favor to send a notification email from <strong><a href=\"#\">"+
                ROEmail+" </a></strong> to <strong><a href=\"#\">"+AmazonEmail+" </a></strong> stating that this issue regarding the above products has been "+
                "resolved? You can mention my store name <strong><a href=\"#\">"+StoreName+" </a></strong> and email <strong><a href=\"#\">"+SellerEmail+" </a></strong> in the email content.  If "+
                "you can cc it to me, that will be perfect.<br><br>"+
                "However, if you feel that there is something more need to be done to resolve this issue, just let me know "+
                "and I will cooperate with you with my best efforts.<br><br>"+
                "Thank you so much for your time and consideration. <br><br>"+
                "Hope to hear from you soon. <br><br>"+
                "Kind Regards,<br><br>"+
                StoreManagerName+" <br>"+
                "Store name:"+StoreName+" <br>"+
                "Store email:"+ SellerEmail+"<br><br>"+
                "============= <br><br>"+
                "Please forward the email to me if the RO responds you later.  I wish you have a wonderful day!<br>"+
                "Best <br><br>"+
                "Infringement Withdrawing Team<br>";

        return html;
    }
    public  String Template3(String keyword1, String keyword2,String infrigement, String ROName,
                             String ROEmail, String StoreManagerName, String StoreName,String ASIN,
                             String title, String AmazonEmail, String SellerEmail, String accountName
    ) {
        String html = "<br>Dear CS,<br>" +
                "   Thanks for your email and kind help. Please reply this email via the following four steps:<br><br>"+
                "1. Please search your seller account inventory and delete every listing related to  the following " +
                "brands:<br>"+
                keyword1+","+ keyword2+" <br>" +
                "(If your account is European Account, please make sure you have done it from UK, ES, IT, DE, FR five "+
                "country inventories.) <br>"+
                "2. Please copy the following table and paste it in the Google sheet\"Infringement Follow up with CS "+
                "Project\" and help to send it regularly if the RO (Right Owner) doesn’t reply it after two days. <br>"+
                infrigement+"<br>"+
                "3. Please copy the email and send it to the following email address as soon as possible: <br>"+
                ROName+" <br>"+
                ROEmail+" <br><br>"+
                "4. Please reply the Amazon's notification email which sent you this infringement case <br>"+
                "· Say warm thanks to their notification; <br>" +
                "· Tell them you have deleted relating products and contacted the right owner to resolve this case. <br><br>"+
                "Note: Please replace all the RED XXXX parts carefully and change the font color from red to black, then  "+
                "read the whole email content TWICE to make sure everything is correct before you send it out.<br><br>"+
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~<br><br>"+
                " Email Title：<br><br>"+
                "Dear  "+ROName+", <br>    I’m sorry and I have immediately deleted the products which violates your"+
                "intellectual property rights <br><br>"+
                "========================== <br><br>"+
                "Dear "+ROName+",<br>"+
                "This is "+StoreManagerName+",the manager of "+StoreName+" (Store name) on Amazon. I received a "+
                "warning from Amazon regarding an infringement case of your products:<br><br>"+
                "ASIN: "+ASIN+" <br>"+
                "Brand: "+title+" <br><br>"+
                "I really apologize for infringing your intellectual property rights. I have deleted all the listings related to you. I "+
                "promise that I will never sell it again. <br><br>"+
                "To be honest, I just got the product database from Amazon other stores which would like me help them" +
                " resell their products. And I generally list them with higher prices at my store. I will place " +
                "orders from their stores if I receive any order from my customer. Due to the large size of product " +
                "listings and lacking of experience, I made such a serious mistake that would cause my account to be " +
                "suspended. I was totally wrong that I should have investigated all of the product listings further " +
                "and check whether we get permission or not.<br><br>"+
                "I sincerely apologize again for all the inconvenience and displeasure has caused to you. I promise we will "+
                "never list your products again at our Amazon store. If it is possible, Could you write a notification email "+
                "from <strong><a href=\"#\">"+ROEmail+" </a></strong> to <strong><a href=\"#\">"+AmazonEmail+" </a></strong> stating that this infringement case has been resolved? Since I "+
                "have deleted all the related products and will never sell them again. When you send the email, please tell "+
                "them the related product asins, my store name and email. And also please cc it to me <strong" +
                "><a href=\"#\"> "+SellerEmail+" </a></strong>. <br><br>"+
                "Thank you very much for your understanding and I look forward to hearing from you soon.<br><br>"+
                "Sincerely,<br>"+
                StoreManagerName+" <br>"+
                "Store name:"+StoreName+" <br>"+
                "Store email:"+ SellerEmail+"<br><br>"+
                "============= <br><br>"+
                "Please forward the email to me if the RO responds you later.  I wish you have a wonderful day! <br>"+
                "Best <br><br>"+
                "Infringement Withdrawing Team<br>";

        return  html;
    }

    public  String Template4(String keyword1, String keyword2,String infrigement, String ROName,
                             String ROEmail, String StoreManagerName, String StoreName,String ASIN,
                             String title, String AmazonEmail, String SellerEmail, String accountName
    ) {
        String html = "<br>Dear CS,<br>" +
                "Thanks for your email and kind help. Please reply this email via the following four steps:<br>" +
                "1. Please search your seller account inventory and delete every listing related to  the following " +
                "brands:<br>" +
                keyword1 + "," + keyword2 + " <br>" +
                "(If your account is European Account, please make sure you have done it from UK, ES, IT, DE, FR five " +
                "country inventories.) <br><br>" +
                "2. Please copy the following table and paste it in the Google sheet\"Infringement Follow up with CS  " +
                "roject\" and help to send it regularly if the RO (Right Owner) doesn’t reply it after two days. <br>" +
                infrigement + "<br><br>" +
                "3. Please copy the email and send it to the following email address as soon as possible: <br>" +
                ROName + " <br>" +
                ROEmail + " <br><br>" +
                "4. Please reply the Amazon's notification email which sent you this infringement case <br>" +
                "· Say warm thanks to their notification; <br>" +
                "· Tell them you have deleted relating products and contacted the right owner to resolve this case. <br><br>" +
                "Note: Please replace all the RED XXXX parts carefully and change the font color from red to black, then " +
                "read the whole email content TWICE to make sure everything is correct before you send it out.<br><br>" +
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~<br><br>" +
                " Email Title：<br><br>" +
                "Dear " + ROName + ",<br>    I'm so sorry for infringing your product property rights. Would you mind accepting my " +
                "sincere apology? <br><br>" +
                "========================<br><br>" +
                "Dear " + ROName + ",<br><br>" +
                "I am so sorry for the infringement case of your brand or trademark on Amazon. As soon as I got the notice " +
                "warning from Amazon,  I immediately deleted all the items which are related to you. I will never sell it again " +
                "unless I get your permission. I checked the order history and found that I haven't got any order for this " +
                "product.<br><br>" +
                "ASIN: " + ASIN + " <br>" +
                "Brand: " + title + " <br><br>" +
                "Due to the lacking of experience and knowledge of IT, it was really my fault that I should have done" +
                " deeper research on my program. My program upload the wrong products info. I sincerely apologize " +
                "again for all the inconvenience it has caused to you and hope to get your understanding.<br><br>" +
                "I really do not want this happen again in the future, since it has brought you much convenience to you and I " +
                "really feel very sorry to you. Therefore, would you please let me know all your brand name list? I want to " +
                "double check and get everything is done for you. <br><br>" +
                "Since this account is really important for me,  could you please help to send an email from <strong" +
                "><a href=\"#\"> " + ROEmail +
                " </a></strong> to <strong><a href=\"#\">" + AmazonEmail + " </a></strong> to withdraw your complaint? So that Amazon will not suspend my selling account. <br><br>" +
                "When you send email to Amazon, please include following relating information and cc it to my store " +
                "email. <br>" +
                "ASIN:" + ASIN + "<br>" +
                "Store name:" + StoreName + " <br>" +
                "Store email:" + SellerEmail + "<br><br>" +
                "Please feel free to let me know if you have any questions, and please let me know if you need any " +
                "assistant . Thank you so much for your time and consideration. I look forward to hearing from you " +
                "soon.<br><br>" +
                "Best Regards,<br>" +
                StoreManagerName + " <br>" +
                "========================<br><br>" +
                "Please forward the email to me if the RO responds you later.  I wish you have a wonderful day! <br>" +
                "Best <br><br>" +
                "Infringement Withdrawing Team<br>";
        return html;
    }

    public  String Template5(String keyword1, String keyword2,String infrigement, String ROName,
                             String ROEmail, String StoreManagerName, String StoreName,String ASIN,
                             String title, String AmazonEmail, String SellerEmail, String accountName
    ) {
        String html = "<br>Dear CS,<br>" +
                "Thanks for your email and kind help. Please reply this email via the following four steps:<br><br>"+
                "1. Please search your seller account inventory and delete every listing related to  the following brands: <br>"+
                keyword1 + "," + keyword2 + " <br>" +
                "(If your account is European Account, please make sure you have done it from UK, ES, IT, DE, FR five "+
                "country inventories.) <br><br>"+
                "2. Please copy the following table and paste it in the Google sheet\"Infringement Follow up with CS  "+
                "Project\" and help to send it regularly if the RO (Right Owner) doesn’t reply it after two days. <br>"+
                infrigement + "<br><br>" +
                "3. Please copy the email and send it to the following email address as soon as possible:<br>"+
                ROName + " <br>" +
                ROEmail + " <br><br>" +
                "4. Please reply the Amazon's notification email which sent you this infringement case <br>"+
                "· Say warm thanks to their notification; <br>" +
                "· Tell them you have deleted relating products and contacted the right owner to resolve this case. <br><br>" +
                "Note: Please replace all the RED XXXX parts carefully and change the font color from red to black, then " +
                "read the whole email content TWICE to make sure everything is correct before you send it out.<br><br>" +
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~<br><br>" +
                " Email Title：<br><br>" +
                "Dear "+ROName+", <br>     regarding the infringement case which related to your products, would you please "+
                "accept my apologize? <br><br>"+
                "======================== <br><br>"+
                "Dear "+ROName+",<br><br>"+
                "My name is "+StoreManagerName+" and I’m a seller on Amazon. I’m writing to you regarding your "+
                "products which were reselling on Amazon.  Amazon recently sent an infringement warning to me, asking "+
                "me to contact you to resolve this issue. The products are: <br><br>"+
                "ASIN: " + ASIN + " <br>" +
                "Brand: " + title + " <br><br>" +
                "I just started my small business on Amazon. Because I don’t have much experience in selecting " +
                "products, I mistakenly listed your products in my store.  I upload the wrong products by accident. " +
                "Now I already improve my program and system, this similar things will never happen.<br><br>"+
                "I wish I could have your understanding that I never deliberately break your property rights. I have removed "+
                "this listing from my Amazon store immediately after I realized reselling it involves infringement issue. Since "+
                "I uploaded product data in bulk, it was difficult to check each listing one by one. However, as a remedy I "+
                "have deleted all products via programing in my product database, so the infringing case will not happen  "+
                "again in the future. <br><br>"+
                "In this case,Could you please accept my sincerely apology? I’m really sorry for you, I do need your help to "+
                "resolve this problem completely. So could you please help to send an email from  <strong" +
                "><a href=\"#\"> "+ROEmail+" </a></strong> to <strong><a href=\"#\">"+
                AmazonEmail + " </a></strong> stating that this infringement case has been resolved? As you know I have deleted all "+
                "your products and I will not resell them again. Could you please help to send the email and  also cc it to <strong><a href=\"#\">"+
                SellerEmail+" </a></strong> so that Amazon will know you are mentioning me? Please remember to list my store "+
                "informaiton and the related products.<br><br>"+
                "Your help will be very much appreciated. If this complaint is withdrawn, Amazon won’t suspend my "+
                "Amazon seller account, otherwise it will become a disaster for my family.<br><br>"+
                "Your help will be very much appreciated.Thanks again for your attention and I look forward to your " +
                "reply.<br><br>"+
                "Have a good day! <br><br>"+
                "Sincerely,<br>"+
                StoreManagerName+" <br>"+
                "Store name:"+StoreName+" <br>"+
                "Store email:"+ SellerEmail+"<br><br>"+
                "========================== <br><br>"+
                "Please forward the email to me if the RO responds you later.  I wish you have a wonderful day!<br>"+
                "Best <br><br>"+
                "Infringement Withdrawing Team <br>";

        return html;
    }

    public  String Template6(String keyword1, String keyword2,String infrigement, String ROName,
                             String ROEmail, String StoreManagerName, String StoreName,String ASIN,
                             String title, String AmazonEmail, String SellerEmail, String accountName
    ) {
        String html = "<br>Dear CS,<br>" +
                "Thanks for your email and kind help. Please reply this email via the following four steps:<br><br>"+
                "1. Please search your seller account inventory and delete every listing related to  the following brands: <br>"+
                keyword1 + "," + keyword2 + " <br>" +
                "(If your account is European Account, please make sure you have done it from UK, ES, IT, DE, FR five "+
                "country inventories.) <br><br>"+
                "2. Please copy the following table and paste it in the Google sheet\"Infringement Follow up with CS "+
                "Project\" and help to send it regularly if the RO (Right Owner) doesn’t reply it after two days. <br>"+
                infrigement + "<br><br>" +
                "3. Please copy the email and send it to the following email address as soon as possible:<br>"+
                ROName + " <br>" +
                ROEmail + " <br><br>" +
                "4. Please reply the Amazon's notification email which sent you this infringement case <br>"+
                "· Say warm thanks to their notification; <br>" +
                "· Tell them you have deleted relating products and contacted the right owner to resolve this case. <br><br>" +
                "Note: Please replace all the RED XXXX parts carefully and change the font color from red to black, then " +
                "read the whole email content TWICE to make sure everything is correct before you send it out.<br><br>" +
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~<br><br>" +
                " Email Title：<br><br>" +
                "Dear "+ROName+", <br>   I sincerely apologize for infringing your intellectual property rights, would you please "+
                "forgive me? <br><br>"+
                "======================== <br><br>"+
                "Dear "+ROName+",<br><br>"+
                "I am "+StoreManagerName+" from Amazon Store "+StoreName+". I’m writing to you as Amazon recently notified "+
                "me that one of my product listing infringed your intellectual property rights. Also, they asked me to "+
                "communicate you first to resolve the issue.The related products are:<br><br>"+
                "ASIN: " + ASIN + " <br>" +
                "Brand: " + title + " <br><br>" +
                "Firstly I sincerely apologize for the property rights infringement issue. Due to the lack of experience, I did "+
                "not notice my listing would be involved in this issue. I just started this small business, and have been "+
                "uploading listing data of other sellers on Amazon website. Actually I  have not made any sale of this  "+
                "product yet. Therefore please believe I had no intention to infringe your property rights. If I had known this "+
                "earlier, I would never list this product on my Amazon store. <br><br>"+
                "Secondly I promise that I will never list this product in my online store again. Once I received the "+
                "notification email from Amazon, I realized the seriousness of the problem. I took immediate action and "+
                "remove this listing from my database. And in the future I will only list products that are safe to " +
                "sell.<br><br>"+
                "Lastly, could you please help me? Amazon said they need one confirmation email from you stating this "+
                "issue is resolved. If possible, could you write an email  from  <strong><a href=\"#\">"+ROEmail+" </a></strong> to <strong><a href=\"#\">" + AmazonEmail + " </a></strong>  with "+
                "my store name <strong><a href=\"#\">"+StoreName+" </a></strong> and account email address <strong" +
                "><a href=\"#\"> "+SellerEmail+" </a></strong> stating the infringement case has "+
                "been resolved? Please also mention the related products information and also cc the email to me. <br><br>"+
                "However, if you need anything else from me to have a better understanding of my business, I will do all "+
                "that I can do to cooperate with you.<br><br>"+
                "I appreciate your time and consideration. Please let me know about your thoughts and I look forward to "+
                "your reply.<br><br>"+
                "Sincerely,<br>"+
                StoreManagerName+"<br><br>"+
                "==================== <br><br>"+
                "Please forward the email to me if the RO responds you later.  I wish you have a wonderful day!<br>"+
                "Best <br><br>"+
                "Infringement Withdrawing Team <br>";

        return html;
    }

    public  String Template7(String keyword1, String keyword2,String infrigement, String ROName,
                             String ROEmail, String StoreManagerName, String StoreName,String ASIN,
                             String title, String AmazonEmail, String SellerEmail, String accountName
    ) {
        String html = "<br>Dear CS,<br>" +
                " Thanks for your email and kind help. Please reply this email via the following four steps:<br><br>"+
                "1. Please search your seller account inventory and delete every listing related to  the following " +
                "brands:<br>"+
                keyword1 + "," + keyword2 + " <br>" +
                "(If your account is European Account, please make sure you have done it from UK, ES, IT, DE, FR five "+
                "country inventories.) <br><br>"+
                "2. Please copy the following table and paste it in the Google sheet\"Infringement Follow up with CS "+
                "Project\" and help to send it regularly if the RO (Right Owner) doesn’t reply it after two days. <br>"+
                infrigement + "<br><br>" +
                "3. Please copy the email and send it to the following email address as soon as possible: <br>"+
                ROName + " <br>" +
                ROEmail + " <br><br>" +
                "4. Please reply the Amazon's notification email which sent you this infringement case <br>"+
                "· Say warm thanks to their notification; <br>" +
                "· Tell them you have deleted relating products and contacted the right owner to resolve this case. <br><br>" +
                "Note: Please replace all the RED XXXX parts carefully and change the font color from red to black, then " +
                "read the whole email content TWICE to make sure everything is correct before you send it out.<br><br>" +
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~<br><br>" +
                " Email Title：<br><br>" +
                "Dear "+ROName+",  I sincerely apologize for the infringement issue related to your product  <br><br>"+
                "======================== <br><br>"+
                "Dear "+ROName+",<br><br>"+
                "This is "+StoreManagerName+", I’m the owner of Amazon store "+StoreName+".  I recently received a "+
                "notification email from Amazon stating that some of my product listings violate your intellectual property "+
                "rights. The products are as following.<br><br>"+
                "ASIN: " + ASIN + " <br>" +
                "Brand: " + title + " <br><br>" +
                "Please allow me to explain my business model here. Due to my lack of knowledge about intellectual "+
                "property rights, I simply reselling what other sellers are selling on Amazon. I did not expect this kind of "+
                "problem may happen and cause such a trouble for us. Actually,  I haven’t received any order of these "+
                "products at my store yet.<br><br>"+
                "After I received the policy warning from Amazon, I immediately removed the listings. If this has caused any "+
                "inconvenience to you, please accept my sincere apology and I will definitely pay extra attention to this "+
                "issue, and avoid this happening again in the future.<br>"+
                "Now Amazon require me to resolve this issue with you. If possible, could you please help to send an email "+
                "from <strong><a href=\"#\">"+ROEmail+" </a></strong> to <strong><a href=\"#\">"+AmazonEmail+" </a></strong> and tell them the issue has been resolved? Please include my "+
                "Amazon seller name <strong><a href=\"#\">"+StoreName+" </a></strong> and email <strong><a href=\"#\"> "+SellerEmail+" </a></strong> in the email and also cc the email to me? Your "+
                "help will be a great significance for me and my family, because without your understanding and "+
                "confirmation email, Amazon would suspend my selling account and I will cannot earn money to feed my "+
                "kids and family.  <br><br>"+
                "I appreciate your time and consideration. I look forward to hearing from you soon.<br><br>"+
                "Kind regards,<br><br>"+
                StoreManagerName+"<br>"+
                StoreName+" (Store name) <br><br>"+
                "========================<br><br>"+
                "Please forward the email to me if the RO responds you later.  I wish you have a wonderful day!<br>"+
                "Best <br><br>"+
                "Infringement Withdrawing Team <br>";

        return html;

    }

    public  String Template8(String keyword1, String keyword2,String infrigement, String ROName,
                             String ROEmail, String StoreManagerName, String StoreName,String ASIN,
                             String title, String AmazonEmail, String SellerEmail, String accountName
    ) {
        String html = "<br>Dear CS,<br>" +
                " Thanks for your email and kind help. Please reply this email via the following four steps:<br><br>"+
                "1. Please search your seller account inventory and delete every listing related to  the following brands: <br>"+
                keyword1 + "," + keyword2 + " <br>" +
                "(If your account is European Account, please make sure you have done it from UK, ES, IT, DE, FR five "+
                "country inventories.) <br><br>"+
                "2. Please copy the following table and paste it in the Google sheet\"Infringement Follow up with CS "+
                "Project\" and help to send it regularly if the RO (Right Owner) doesn’t reply it after two days. <br>"+
                infrigement + "<br><br>" +
                "3. Please copy the email and send it to the following email address as soon as possible:<br>"+
                ROName + " <br>" +
                ROEmail + " <br><br>" +
                "4. Please reply the Amazon's notification email which sent you this infringement case <br>"+
                "· Say warm thanks to their notification; <br>" +
                "· Tell them you have deleted relating products and contacted the right owner to resolve this case. <br><br>" +
                "Note: Please replace all the RED XXXX parts carefully and change the font color from red to black, then " +
                "read the whole email content TWICE to make sure everything is correct before you send it out.<br><br>" +
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~<br><br>" +
                " Email Title：<br><br>" +
                "Dear "+ROName+", <br>    would you mind accepting my sincere apology about infringing your intellectual "+
                "property rights?<br><br>"+
                " ================<br><br>"+
                "Dear "+ROName+",<br><br>"+
                " This is  "+StoreManagerName+" from a Amazon store.  I got a notice from Amazon that I need to "+
                "contact you to resolve an infringement case. The related products are: <br><br>"+
                "ASIN: " + ASIN + " <br>" +
                "Brand: " + title + " <br><br>" +
                "Once I got the notification, I was shocked and nervous. I checked my inventory and deleted all the relating "+
                "products immediately. Now I can confirm that there is not any of your  products in my store. I am so sorry "+
                "for this case happened, and I have no excuse for my mistake.<br><br>"+
                "Please do believe that I did not intend to violate your property rights.  Now I know it is very serious and "+
                "high risky for my selling account, since it may be suspending by Amazon. When I started doing Amazon "+
                "reselling business online, I did not realize reselling what other stores’ products on Amazon can cause this "+
                "problem. However, now I have learned a lesson. I will be very seriously for selecting products to sell in the "+
                "future. <br><br>"+
                "I am really sad for the situation now since my account is facing being suspended by Amazon. Could you "+
                "please help me this time?  If you think this problem is resolved, can you please send a confirmation email "+
                "from <strong><a href=\"#\">"+ROEmail+" </a></strong> to Amazon <strong><a href=\"#\">"+AmazonEmail+" </a></strong>  and tell them this problem is resolved? My seller "+
                "name is <strong><a href=\"#\">"+StoreName+" </a></strong> and my email address is <strong><a href=\"#\">"+SellerEmail+" </a></strong>. Please has my store information and also the "+
                "above relating products in the email when you send email to Amazon.  Could you please also cc it to me "+
                "when you send it to Amazon? <br><br>"+
                "Again, I humbly apologize for the inconvenience that I have brought to you. I look forward to getting your "+
                "response soon. <br><br>"+
                "Have a great day! <br><br>"+
                "Thanks,<br>"+
                StoreManagerName+"<br><br>"+
                "================ <br><br>"+
                "Please forward the email to me if the RO responds you later.  I wish you have a wonderful day!<br>"+
                "Best <br><br>"+
                "Infringement Withdrawing Team <br>";
        return html;
    }

    public  String Template9(String keyword1, String keyword2,String infrigement, String ROName,
                             String ROEmail, String StoreManagerName, String StoreName,String ASIN,
                             String title, String AmazonEmail, String SellerEmail, String accountName
    ) {
        String html = "<br>Dear CS,<br>" +
                "Thanks for your email and kind help. Please reply this email via the following four steps:<br><br>"+
                "1. Please search your seller account inventory and delete every listing related to  the following " +
                "brands:<br>"+
                keyword1 + "," + keyword2 + " <br>" +
                "(If your account is European Account, please make sure you have done it from UK, ES, IT, DE, FR five "+
                "country inventories.) <br><br>"+
                "2. Please copy the following table and paste it in the Google sheet\"Infringement Follow up with CS "+
                "Project\" and help to send it regularly if the RO (Right Owner) doesn’t reply it after two days. <br>"+
                infrigement + "<br><br>" +
                "3. Please copy the email and send it to the following email address as soon as possible:<br>"+
                ROName + " <br>" +
                ROEmail + " <br><br>" +
                "4. Please reply the Amazon's notification email which sent you this infringement case <br>"+
                "· Say warm thanks to their notification; <br>" +
                "· Tell them you have deleted relating products and contacted the right owner to resolve this case. <br><br>" +
                "Note: Please replace all the RED XXXX parts carefully and change the font color from red to black, then " +
                "read the whole email content TWICE to make sure everything is correct before you send it out.<br><br>" +
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~<br><br>" +
                " Email Title：<br><br>" +
                "Dear "+ROName+",<br><br>"+
                "This is "+StoreManagerName+", I have a Amazon store "+StoreName+"( Store Name). I received a notification "+
                "from Amazon recently. I am not sure what to do since this is a very serious infringement case. The relating "+
                "products are: <br><br>"+
                "ASIN: " + ASIN + " <br>" +
                "Brand: " + title + " <br><br>" +
                "I looked into the case as soon as I got the Amazon notice and found I made a mistake about listing your "+
                "products in my store. Once I realized that, I deleted all the relating products from my store immediately. I "+
                "wish to get your understanding and forgiveness about this case. Since I resell other Amazon sellers’ "+
                "products in my store. <br><br>"+
                "I know nothing could compensate for the disappointment and frustration that I gave to you. I could only say, "+
                "\"I am so sorry for everything.\" I have deleted the relating products and I promise that I will not" +
                " list them in "+
                "my store again. Could you please help me this time?<br><br>"+
                "Now Amazon is requiring me to resolve the issue with you, otherwise, they may suspend my selling "+
                "account.  With your kindness and forgiveness, could you please help to send a confirmation email from <strong><a href=\"#\">"+
                ROEmail+" </a></strong> to <strong><a href=\"#\">"+AmazonEmail+" </a></strong> telling the the infringing issue has been resolved with my store? <br>"+
                "Please mention the following information in the email.  <br><br>"+
                "ASIN: " + ASIN + " <br>" +
                "Brand: " + title + " <br><br>" +
                "My Store name: "+StoreName+"<br>"+
                "My Store email: "+SellerEmail+"<br>"+
                "When you send the email, please also cc it to my store email so that I can followup with Amazon to "+
                "confirm the issue was resolved. <br><br>"+
                "Thank you so much for your time and consideration. I look forward to getting your response soon. <br><br>"+
                "Sincerely,<br>"+
                StoreManagerName+"<br><br>"+
                "========================<br><br>"+
                "Please forward the email to me if the RO responds you later.  I wish you have a wonderful day!<br>"+
                "Best<br><br>"+
                "Infringement Withdrawing Team<br>";
        return html;
    }

    public  String Template10(String keyword1, String keyword2,String infrigement, String ROName,
                             String ROEmail, String StoreManagerName, String StoreName,String ASIN,
                             String title, String AmazonEmail, String SellerEmail, String accountName
    ) {
        String html = "<br>Dear CS,<br>" +
                "Thanks for your email and kind help. Please reply this email via the following four steps:<br><br>"+
                "1. Please search your seller account inventory and delete every listing related to  the following brands: <br>"+
                keyword1 + "," + keyword2 + " <br>" +
                "(If your account is European Account, please make sure you have done it from UK, ES, IT, DE, FR five "+
                "country inventories.) <br><br>"+
                "2. Please copy the following table and paste it in the Google sheet\"Infringement Follow up with CS "+
                "Project\" and help to send it regularly if the RO (Right Owner) doesn’t reply it after two days. <br>"+
                infrigement + "<br><br>" +
                "3. Please copy the email and send it to the following email address as soon as possible:<br>"+
                ROName + " <br>" +
                ROEmail + " <br><br>" +
                "4. Please reply the Amazon's notification email which sent you this infringement case<br>"+
                "· Say warm thanks to their notification; <br>" +
                "· Tell them you have deleted relating products and contacted the right owner to resolve this case. <br><br>" +
                "Note: Please replace all the RED XXXX parts carefully and change the font color from red to black, then " +
                "read the whole email content TWICE to make sure everything is correct before you send it out.<br><br>" +
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~<br><br>" +
                " Email Title：<br><br>" +
                "Dear "+ROName+",<br>    I am really sorry for the property rights violation to your products.<br><br>"+
                "======================== <br><br>"+
                "Dear "+ROName+",<br><br>"+
                " This is "+StoreManagerName+" from the Amazon store named "+StoreName+". I just received an infringing "+
                "notification email from Amazon regarding your following products: <br><br>"+
                "ASIN: " + ASIN + " <br>" +
                "Brand: " + title + " <br><br>" +
                " I am so sorry for accidentally listing your products in my store. Please believe I really did not intend to "+
                "violate your property rights. Once I received the notification, I have immediately deleted all the relating "+
                "products. I have this Amazon reselling store without holding any physical inventory. I did not realize it may "+
                "cause such great inconvenience to you. I have no excuse to explain my mistake and I have learned a "+
                "lesson from this case.<br><br>"+
                "Now Amazon require me to contact you to resolve this issue. Otherwise, they may suspend my selling "+
                "account. As you know, I have deleted all the products relating to your brand, and I guarantee will never list "+
                "them again in the future.  In this case, could you please send a confirmation email from <strong" +
                "><a href=\"#\"> "+ROEmail+" </a></strong> to <strong><a href=\"#\">"+
                AmazonEmail+" </a></strong> stating the issue has been resolved?  I promise that I will never sell your products "+
                "again in the future.  If you could help to send the email, please cc it to me and mention the relating product "+
                "info, my store name and email in the content.<br><br>"+
                "Thank you very much and hope to hear from you soon. <br><br>"+
                "Sincerely,<br>"+
                StoreManagerName+"<br>"+
                "Store name: "+StoreName+"<br>"+
                "Store email address:"+SellerEmail+"<br><br>"+
                "========================<br><br>"+
                "Please forward the email to me if the RO responds you later.  I wish you have a wonderful day!<br>"+
                "Best <br><br>"+
                "Infringement Withdrawing Team<br>";

        return html;
    }

    public  String Template11(String keyword1, String keyword2,String infrigement, String ROName,
                              String ROEmail, String StoreManagerName, String StoreName,String ASIN,
                              String title, String AmazonEmail, String SellerEmail, String accountName
    ) {
        String html = "<br>Dear CS,<br>" +
                " Thanks for your email and kind help. Please reply this email via the following four steps:<br><br>"+
                "1. Please search your seller account inventory and delete every listing related to  the following brands: <br>"+
                keyword1 + "," + keyword2 + " <br>" +
                "(If your account is European Account, please make sure you have done it from UK, ES, IT, DE, FR five "+
                "country inventories.) <br><br>"+
                "2. Please copy the following table and paste it in the Google sheet\"Infringement Follow up with CS "+
                "Project\" and help to send it regularly if the RO (Right Owner) doesn’t reply it after two days.<br>"+
                infrigement + "<br><br>" +
                "3. Please copy the email and send it to the following email address as soon as possible:<br>"+
                ROName + " <br>" +
                ROEmail + " <br><br>" +
                "4. Please reply the Amazon's notification email which sent you this infringement case <br>"+
                "· Say warm thanks to their notification; <br>" +
                "· Tell them you have deleted relating products and contacted the right owner to resolve this case. <br><br>" +
                "Note: Please replace all the RED XXXX parts carefully and change the font color from red to black, then " +
                "read the whole email content TWICE to make sure everything is correct before you send it out.<br><br>" +
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~<br><br>" +
                " Email Title：<br><br>" +
                "Dear "+ROName+", I humbly apologize for the property rights violation. Will you accept my sincere "+
                "apology and help to send an email to Amazon? <br><br>"+
                "========================<br><br>"+
                "Dear "+ROName+",<br><br>"+
                "This is "+StoreManagerName+" from Amazon store "+StoreName+". I just received a notification from Amazon "+
                "regarding your property rights infringement. I feel so sorry for the accident. Please do believe that I did not "+
                "intend to do that since the violation is so risky for my selling account which may lead my account be "+
                "suspended by Amazon. The relating products are:  <br><br>"+
                "ASIN: " + ASIN + " <br>" +
                "Brand: " + title + " <br><br>" +
                "Once I received the notification, I checked my inventory and deleted all the relating products immediately. I "+
                "guarantee that I will never list them again in the future.<br><br>"+
                "Amazon have let me resolve this case with you. Since I have deleted all the relating products and will "+
                "never upload or resell them again in the future, with your mercy and understanding, could you please help "+
                "to send an email from <strong><a href=\"#\">"+ROEmail+" </a></strong> to <strong><a href=\"#\"> "+AmazonEmail+" </a></strong> and tell them the case is resolved? May I ask "+
                "your forgiveness to send this email for me? If possible, could you please also cc it to me when you send "+
                "the email to Amazon. Please mention my store information and the relating products in the email " +
                "content. <br><br>"+
                "I wish to hear from you with the good news as soon as possible.<br><br>"+
                "Sincerely,<br><br>"+
                StoreManagerName+"<br>"+
                "Store Name: "+StoreName+"<br>"+
                "Store Email: "+SellerEmail+"<br><br>"+
                "========================<br><br>"+
                "Please forward the email to me if the RO responds you later.  I wish you have a wonderful day!<br>"+
                "Best<br><br>"+
                "Infringement Withdrawing Team <br>";

        return html;
    }

    public  String Template12(String keyword1, String keyword2,String infrigement, String ROName,
                              String ROEmail, String StoreManagerName, String StoreName,String ASIN,
                              String title, String AmazonEmail, String SellerEmail, String accountName
    ) {
        String html = "<br>Dear CS,<br>" +
                " Thanks for your email and kind help. Please reply this email via the following four steps:<br><br>"+
                "1. Please search your seller account inventory and delete every listing related to  the following " +
                "brands:<br>"+
                keyword1 + "," + keyword2 + " <br>" +
                "(If your account is European Account, please make sure you have done it from UK, ES, IT, DE, FR five "+
                "country inventories.) <br><br>"+
                "2. Please copy the following table and paste it in the Google sheet\"Infringement Follow up with CS "+
                "Project\" and help to send it regularly if the RO (Right Owner) doesn’t reply it after two days. <br>"+
                infrigement + "<br><br>" +
                "3. Please copy the email and send it to the following email address as soon as possible: <br>"+
                ROName + " <br>" +
                ROEmail + " <br><br>" +
                "4. Please reply the Amazon's notification email which sent you this infringement case <br>"+
                "· Say warm thanks to their notification; <br>" +
                "· Tell them you have deleted relating products and contacted the right owner to resolve this case. <br><br>" +
                "Note: Please replace all the RED XXXX parts carefully and change the font color from red to black, then " +
                "read the whole email content TWICE to make sure everything is correct before you send it out.<br><br>" +
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~<br><br>" +
                " Email Title：<br><br>" +
                "Dear "+ROName+", could you please help me to resolve the property rights infringement case? <br><br>"+
                "======================== <br><br>"+
                "Dear "+ROName+",<br><br>"+
                " This is "+StoreManagerName+" from Amazon store "+StoreName+"(Store name). I received an email regarding"+
                "an infringing issue about your following products:<br><br>"+
                "ASIN: " + ASIN + " <br>" +
                "Brand: " + title + " <br><br>" +
                " I am so sorry for the inconvenience caused to you. Once I received the email, I deleted the relating listings "+
                "immediately. I repent my fault and I wish I would have never listed your products in my Amazon store. Now "+
                "I hope to get your understanding and mercy since I only do Amazon reselling, I resell what other Amazon "+
                "sellers are selling. I am so sorry for the problem caused to you. Could you please forgive me this " +
                "time?<br><br>"+
                " Since I really need this account to earn money to feed my family. However, if this case couldn’t be "+
                "resolved with you well, Amazon may suspend my account. With your forgiveness, could you please help to "+
                "send an email to Amazon and tell them this case is resolved? Also when you send the email, please "+
                "remember to send it from  <strong><a href=\"#\">"+ROEmail+" </a></strong> to <strong><a href=\"#\"> "+AmazonEmail+" </a></strong> and include the relating products and "+
                "my store information in the content. The relating products are: <br><br>"+
                "ASIN: " + ASIN + " <br>" +
                "Brand: " + title + " <br><br>" +
                "My Store name: "+StoreName+"<br>"+
                "My Store email: "+SellerEmail+"<br><br>"+
                " I believe that you are a kind person and will help me like an angel. Please feel free to let me know if there "+
                "is anything else I could do for you. <br>"+
                "I look forward to getting your response soon. <br><br>"+
                "Sincerely,<br>"+
                StoreManagerName+"<br><br>"+
                "========================<br><br>"+
                "Please forward the email to me if the RO responds you later.  I wish you have a wonderful day! <br>"+
                "Best <br><br>"+
                "Infringement Withdrawing Team <br>";

        return html;
    }

    public  String Template13(String keyword1, String keyword2,String infrigement, String ROName,
                              String ROEmail, String StoreManagerName, String StoreName,String ASIN,
                              String title, String AmazonEmail, String SellerEmail, String accountName
    ) {
        String html = "<br>Dear CS,<br>" +
                " Thanks for your email and kind help. Please reply this email via the following four steps:<br><br>"+
                "1. Please search your seller account inventory and delete every listing related to  the following brands: <br>"+
                keyword1 + "," + keyword2 + " <br>" +
                "(If your account is European Account, please make sure you have done it from UK, ES, IT, DE, FR five "+
                "country inventories.) <br><br>"+
                "2. Please copy the following table and paste it in the Google sheet\"Infringement Follow up with CS "+
                "Project\" and help to send it regularly if the RO (Right Owner) doesn’t reply it after two days.<br>"+
                infrigement + "<br><br>" +
                "3. Please copy the email and send it to the following email address as soon as possible:<br>"+
                ROName + " <br>" +
                ROEmail + " <br><br>" +
                "4. Please reply the Amazon's notification email which sent you this infringement case <br>"+
                "· Say warm thanks to their notification; <br>" +
                "· Tell them you have deleted relating products and contacted the right owner to resolve this case. <br><br>" +
                "Note: Please replace all the RED XXXX parts carefully and change the font color from red to black, then " +
                "read the whole email content TWICE to make sure everything is correct before you send it out.<br><br>" +
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~<br><br>" +
                " Email Title：<br><br>" +
                "Dear "+ROName+", I'm so sorry for the Property right infringement, could you please forgive me this "+
                "time? <br><br>"+
                "========================== <br><br>"+
                "Dear "+ROName+",<br><br>"+
                " My name is "+StoreManagerName+" from Amazon store "+StoreName+". I'm sorry for disturbing you.  I got an "+
                "infringement warning from Amazon which was regarding the following product: <br><br>"+
                "ASIN: " + ASIN + " <br>" +
                "Brand: " + title + " <br><br>" +
                "Amazon let me contact you to resolve this issue with you for avoiding my account be suspended by them. "+
                "Actually, as you know I removed this product and also the related ones from my inventory as soon as I got "+
                "the warning. <br><br>"+
                "My business model is resell other Amazon sellers' products, I actually would buy the product from other "+
                "Amazon sellers once I got an order. I felt very very sorry for bringing trouble to you. I guarantee I will never "+
                "list or sell it again in the future. In this case, could you please help to send a confirmation email from <strong><a href=\"#\">"+
                ROEmail+" </a></strong> to <strong><a href=\"#\">"+AmazonEmail+" </a></strong> and tell them the issue has been resolved? <br><br>"+
                "I do apologize for what I did wrong and I hope to get your mercy and understand. If you could help to send "+
                "the email, please tell them my store name <strong><a href=\"#\">"+StoreName+" </a></strong>, store email <strong><a href=\"#\">"+SellerEmail+" </a></strong> and the above " +
                "relating "+
                "products. Please also CC the email to me when you send it. <br><br>"+
                "I hope to get your response soon. <br><br>"+
                "Best, <br> "+
                StoreManagerName+"<br><br>"+
                "==========================<br><br>"+
                "Please forward the email to me if the RO responds you later.  I wish you have a wonderful day!<br>"+
                "Best <br><br>"+
                "Infringement Withdrawing Team <br> ";

        return html;
    }

    public  String Template14(String keyword1, String keyword2,String infrigement, String ROName,
                              String ROEmail, String StoreManagerName, String StoreName,String ASIN,
                              String title, String AmazonEmail, String SellerEmail, String accountName
    ) {
        String html = "<br>Dear CS,<br>" +
                " Thanks for your email and kind help. Please reply this email via the following four steps:<br><br>"+
                "1. Please search your seller account inventory and delete every listing related to  the following " +
                "brands:<br>"+
                keyword1 + "," + keyword2 + " <br>" +
                "(If your account is European Account, please make sure you have done it from UK, ES, IT, DE, FR five "+
                "country inventories.) <br><br>"+
                "2. Please copy the following table and paste it in the Google sheet\"Infringement Follow up with CS "+
                "Project\" and help to send it regularly if the RO (Right Owner) doesn’t reply it after two days. <br>"+
                infrigement + "<br><br>" +
                "3. Please copy the email and send it to the following email address as soon as possible:<br>"+
                ROName + " <br>" +
                ROEmail + " <br><br>" +
                "4. Please reply the Amazon's notification email which sent you this infringement case<br>"+
                "· Say warm thanks to their notification; <br>" +
                "· Tell them you have deleted relating products and contacted the right owner to resolve this case. <br><br>" +
                "Note: Please replace all the RED XXXX parts carefully and change the font color from red to black, then " +
                "read the whole email content TWICE to make sure everything is correct before you send it out.<br><br>" +
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~<br><br>" +
                " Email Title：<br><br>" +
                "Dear "+ROName+", I'm sorry for the Amazon infringement issue about your products, could you please "+
                "help me resolve this issue?<br><br>"+
                "================<br><br>"+
                "Dear "+ROName+", <br>"+
                " This is "+StoreManagerName+", I have an Amazon store "+StoreName+". Recently I received an infringement "+
                "warning from Amazon which said I infringed your property rights.  Amazon let me resolve this issue with "+
                "you as soon as possible, otherwise, they may suspend my account. The related products are:<br><br>"+
                "ASIN: " + ASIN + " <br>" +
                "Brand: " + title + " <br><br>" +
                " I'm really very sorry for this issue happened due to my lacking of experience and knowledge about "+
                "infringement. It is really my fault that I should not upload your brand products to my store.  Actually, as I do "+
                "Amazon reselling business, I planned to buy your brand products from other Amazon sellers and would ask "+
                "them to ship the products directly to my customers if I got any orders. However, I didn't realize it infringed "+
                "your property rights.  It is really my fault that I should have done deeper research on this. I sincerely "+
                "apologize for the damage caused to you. <br><br>"+
                "I have deleted all the relating products after I got the warning. I guarantee that I will never sell your "+
                "products without your permission again from now on.  In this case, could you please do me a favor by "+
                "sending an email to Amazon? Since my Amazon selling account is really important for my family's survive "+
                "and I really don't want it's suspended. I sincerely beg for your mercy to give me a second chance.  <br><br>"+
                "I really need your understanding and help by sending an email to Amazon stating the issue has been "+
                "resolved.  Amazon required the email must be sent from  <strong><a href=\"#\">"+ROEmail+" </a></strong> to  <strong><a href=\"#\">"+AmazonEmail+" </a></strong> which "+
                "should also include my store name <strong><a href=\"#\">"+StoreName+" </a></strong>, store email <strong><a href=\"#\">"+SellerEmail+" </a></strong> and above relating products "+
                "inside. Could you please do me a favor for this and also CC the email to me?<br><br>"+
                "Thanks for your understanding and help in advance. I look forward to hearing from you soon. <br><br>"+
                "Sincerely,   <br>"+
                StoreManagerName+"<br><br>"+
                "============================= <br><br>"+
                "Please forward the email to me if the RO responds you later.  I wish you have a wonderful day!<br>"+
                "Best<br><br>"+
                "Infringement Withdrawing Team <br>";

        return html;
    }

    public  String Template15(String keyword1, String keyword2,String infrigement, String ROName,
                              String ROEmail, String StoreManagerName, String StoreName,String ASIN,
                              String title, String AmazonEmail, String SellerEmail, String accountName
    ) {
        String html = "<br>Dear CS,<br>" +
                " Thanks for your email and kind help. Please reply this email via the following four steps:<br><br>"+
                "1. Please search your seller account inventory and delete every listing related to  the following brands: <br>"+
                keyword1 + "," + keyword2 + " <br>" +
                "(If your account is European Account, please make sure you have done it from UK, ES, IT, DE, FR five "+
                "country inventories.) <br><br>"+
                "2. Please copy the following table and paste it in the Google sheet\"Infringement Follow up with CS "+
                "Project\" and help to send it regularly if the RO (Right Owner) doesn’t reply it after two days. "+
                infrigement + "<br><br>" +
                "3. Please copy the email and send it to the following email address as soon as possible:<br>"+
                ROName + " <br>" +
                ROEmail + " <br><br>" +
                "4. Please reply the Amazon's notification email which sent you this infringement case<br>"+
                "· Say warm thanks to their notification; <br>" +
                "· Tell them you have deleted relating products and contacted the right owner to resolve this case. <br><br>" +
                "Note: Please replace all the RED XXXX parts carefully and change the font color from red to black, then " +
                "read the whole email content TWICE to make sure everything is correct before you send it out.<br><br>" +
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~<br><br>" +
                " Email Title：<br><br>" +
                "Hello "+ROName+", I apologize for the inconvenience caused to you <br><br>"+
                "==========================<br><br>"+
                "Hello "+ROName+",<br><br>"+
                " My name is "+StoreManagerName+", I’m a seller on Amazon and my store name is "+StoreName+". I contact "+
                "you because I just received a notification from Amazon regarding your following products: <br><br>"+
                "ASIN: " + ASIN + " <br>" +
                "Brand: " + title + " <br><br>" +
                " I’m so sorry for this happened and I sincerely apologize for my mistake. I have deleted it from my inventory "+
                "immediately after I got the notice.  I also searched my inventory and deleted all relating items. Now "+
                "Amazon require me to resolve this infringing case with yo, as they may suspend my account if it couldn’t "+
                "be resolved quickly. <br><br>"+
                "I apologize again for all the inconvenience caused to you.  I really want to resolve this problem as soon as "+
                "possible. My business model is reselling other Amazon sellers' products. I actually would buy the product "+
                "from other Amazon seller once I got an order. I felt very very sorry for bringing trouble to you. I guarantee I "+
                "will never list or sell your products again in the future. <br><br>"+
                "If you consider this problem is resolved, could you please help to send an email from <strong" +
                "><a href=\"#\">"+ROEmail+" </a></strong> to <strong><a href=\"#\">"+
                AmazonEmail+" </a></strong> stating the infringing case has been resolved? And please mention the relating "+
                "products, my store information in the email and also cc it to <strong><a href=\"#\">"+SellerEmail+" </a></strong>. However, if there is "+
                "anything else need me to finish before you sending the confirmation email, please feel free to let " +
                "me know. <br>"+
                "Thank you very much for your understanding and I look forward to hearing from you soon.<br><br>"+
                "Sincerely,<br>"+
                StoreManagerName+"<br>"+
                "Seller name:"+StoreName+"<br>"+
                "Seller email:"+ SellerEmail+"<br>"+
                "==========================<br><br>"+
                "Please forward the email to me if the RO responds you later.  I wish you have a wonderful day!<br>"+
                "Best <br><br>"+
                "Infringement Withdrawing Team<br>";

        return html;
    }

    public  String Template16(String keyword1, String keyword2,String infrigement, String ROName,
                              String ROEmail, String StoreManagerName, String StoreName,String ASIN,
                              String title, String AmazonEmail, String SellerEmail, String accountName
    ) {
        String html = "<br>Dear CS,<br>" +
                "  Thanks for your email and kind help. Please reply this email via the following four steps:<br><br>"+
                "1. Please search your seller account inventory and delete every listing related to  the following " +
                "brands:<br>"+
                keyword1 + "," + keyword2 + " <br>" +
                "(If your account is European Account, please make sure you have done it from UK, ES, IT, DE, FR five "+
                "country inventories.) <br><br>"+
                "2. Please copy the following table and paste it in the Google sheet\"Infringement Follow up with CS "+
                "Project\" and help to send it regularly if the RO (Right Owner) doesn’t reply it after two days. <br>"+
                infrigement + "<br><br>" +
                "3. Please copy the email and send it to the following email address as soon as possible:<br>"+
                ROName + " <br>" +
                ROEmail + " <br><br>" +
                "4. Please reply the Amazon's notification email which sent you this infringement case<br>"+
                "· Say warm thanks to their notification; <br>" +
                "· Tell them you have deleted relating products and contacted the right owner to resolve this case. <br><br>" +
                "Note: Please replace all the RED XXXX parts carefully and change the font color from red to black, then " +
                "read the whole email content TWICE to make sure everything is correct before you send it out.<br><br>" +
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~<br><br>" +
                " Email Title：<br><br>" +
                "Dear "+ROName+", could you please kindly read my email and let me know what to do to resolve the "+
                "problem with you? <br><br>"+
                "==========================<br>"+
                "Dear "+ROName+",<br><br>"+
                " I am "+StoreManagerName+" from Amazon store "+StoreName+".  Recently I got an Amazon policy warning "+
                "which said one of my listings infringed your property rights.  Amazon also asked me to contact you directly "+
                "to resolve the issue. Otherwise, they will suspend my account. The related products are:<br><br>"+
                "ASIN: " + ASIN + " <br>" +
                "Brand: " + title + " <br><br>" +
                "I am really sorry for the trouble caused to you.  That was my fault that I uploaded those whatever were "+
                "selling at Amazon, but I did not investigate them deeply for the intellectual property rights. I immediately "+
                "deleted the products and any other relating listings after I got Amazon warning. I will be very seriously for "+
                "uploading new products in the future and I wish this kind of case will never happen again from now " +
                "on. <br><br>"+
                "Since there will be no any infringing issue from my store to you in the future, could you please kindly "+
                "forgive me this time?  I don’t have any of your products in my store now. <br><br>"+
                "If you think this problem was resolved, could you please do me a favor to send an email from <strong" +
                "><a href=\"#\"> "+ ROEmail+" </a></strong> to <strong><a href=\"#\">"+AmazonEmail+" </a></strong> and tell Amazon this issue is resolved? My seller name is <strong><a href=\"#\">"+StoreName+
                " </a></strong> and seller email is <strong><a href=\"#\">"+SellerEmail+" </a></strong>.  Would you please cc me the email as well?  Otherwise, would you <br>"+
                "please let me know what else I need to do to resolve this case? <br><br>"+
                "Thanks for your understanding and help in advance. <br>"+
                "I look forward to getting your response soon.<br><br>"+
                "Best,<br>"+
                StoreManagerName+"<br><br>"+
                "==========================<br><br>"+
                "Please forward the email to me if the RO responds you later.  I wish you have a wonderful day!<br>"+
                "Best<br><br>"+
                "Infringement Withdrawing Team<br>";

        return html;
    }

    public  String Template17(String keyword1, String keyword2,String infrigement, String ROName,
                              String ROEmail, String StoreManagerName, String StoreName,String ASIN,
                              String title, String AmazonEmail, String SellerEmail, String accountName
    ) {
        String html = "<br>Dear CS,<br>" +
                "  Thanks for your email and kind help. Please reply this email via the following four steps:<br><br>"+
                "1. Please search your seller account inventory and delete every listing related to  the following " +
                "brands:<br>"+
                keyword1 + "," + keyword2 + " <br>" +
                "(If your account is European Account, please make sure you have done it from UK, ES, IT, DE, FR five "+
                "country inventories.) <br><br>"+
                "2. Please copy the following table and paste it in the Google sheet\"Infringement Follow up with CS "+
                "Project\" and help to send it regularly if the RO (Right Owner) doesn’t reply it after two days. <br>"+
                infrigement + "<br><br>" +
                "3. Please copy the email and send it to the following email address as soon as possible:<br>"+
                ROName + " <br>" +
                ROEmail + " <br><br>" +
                "4. Please reply the Amazon's notification email which sent you this infringement case<br>"+
                "· Say warm thanks to their notification; <br>" +
                "· Tell them you have deleted relating products and contacted the right owner to resolve this case. <br><br>" +
                "Note: Please replace all the RED XXXX parts carefully and change the font color from red to black, then " +
                "read the whole email content TWICE to make sure everything is correct before you send it out.<br><br>" +
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~<br><br>" +
                " Email Title：<br><br>" +
                "Dear "+ROName+", I’m sorry and I have immediately deleted the products which violates your " +
                "intellectual property rights. <br><br>"+
                "==========================<br>"+
                "Dear "+ROName+",<br><br>"+
                "This is "+StoreManagerName+", the manager of "+StoreName+"(Store name) on Amazon. I received a " +
                "warning from Amazon regarding an infringement case of your products:<br><br>"+
                "ASIN: " + ASIN + " <br>" +
                "Brand: " + title + " <br><br>" +
                "I really apologize for infringing your intellectual property rights. I have deleted all the listings" +
                " related to you. I promise that I will never sell it again. <br><br>"+
                "To be honest, I just got the product database from Amazon other stores which we already make " +
                "agreement with and list their products with higher prices at my store. I will place orders from " +
                "their stores if I receive any order from my customer. Due to the large size of product listings and " +
                "lacking of experience, I made such a serious mistake that would cause upload unauthorized products. " +
                "I was totally wrong that I should have investigated all of the product listings further and " +
                "researched much deeper on products when I upload. <br><br>"+
                "I sincerely apologize again for all the inconvenience and displeasure has caused to you. I promise we will never let this similar things " +
                "happen again at our Amazon store. If it is possible, Could you write a notification email from <strong" +
                "><a href=\"#\"> "+ ROEmail+" </a></strong> to <strong><a href=\"#\">"+AmazonEmail+" </a></strong> stating that " +
                "this infringement case has been resolved? Since I have deleted all the related products and will never sell them again. When you send the email, " +
                "please tell them the related product asins, my store name("+StoreName+") and email(<strong><a href=\"#\">"+SellerEmail+" </a></strong>). And also please cc it to me.  <br><br>"+
                "Thank you very much for your understanding and I look forward to hearing from you soon.<br><br>"+
                "Sincerely,<br>"+
                StoreManagerName+"<br>"+
                "Store name:"+StoreName+"<br>"+
                "Store email:"+SellerEmail+"<br><br>"+
                "==========================<br><br>"+
                "Please forward the email to me if the RO responds you later.  I wish you have a wonderful day!<br>"+
                "Best<br><br>"+
                "Infringement Withdrawing Team<br>";

        return html;
    }

    public  String Template100(String keyword1, String keyword2,String infrigement, String ROName,
                              String ROEmail, String StoreManagerName, String StoreName,String ASIN,
                              String title, String AmazonEmail, String SellerEmail, String accountName
    ) {
        String html = "<br>Dear CS,<br>" +
                " Thank you for your kind help. Please send the following email to Amazon to get the relating products" +
                " data: <br><br>"+
                "======================== <br><br>"+
                "Dear Amazon seller performance, <br><br>"+
                "Thank you for your notification. <br><br>"+
                "Per your request, I want to remove the listing and contact the Right owner.  And now I am wondering  what "+
                "the products asin and title are related to case?<br><br>"+
                "Thank you very much and hope to hear from you soon.<br><br>"+
                "Sincerely, <br>"+
                "Store name: "+StoreName+"<br><br>"+
                "========================<br><br>"+
                " Please forward the email to me if the RO responds you later.  I wish you have a wonderful day!<br>"+
                "Best<br><br>"+
                "Infringement Withdrawing Team <br>";


        return html;
    }

    public  String Template101(String keyword1, String keyword2,String infrigement, String ROName,
                               String ROEmail, String StoreManagerName, String StoreName,String ASIN,
                               String title, String AmazonEmail, String SellerEmail, String accountName
    ) {
        String html = "<br>Dear CS,<br>" +
                "Thank you for your kind help. Please send the following email to Amazon to get the Right Owner info: <br><br>"+
                "======================== <br><br>"+
                "Dear Amazon seller performance, <br><br>"+
                "Thank you for your notification and kind help. <br><br>"+
                "Per your request, I have removed the listings. Now I want to contact the right owner to resolve this case,   <br><br>"+
                "could you please send me the right owner name and contact email address? <br><br>"+
                "Thank you very much for your support. <br><br>"+
                "Kind Regards, <br>"+
                "Store name: "+StoreName+"<br><br>"+
                "========================<br><br>"+
                "Please forward the email to me if the RO responds you later.  I wish you have a wonderful day!<br>"+
                "Best<br><br>"+
                "Infringement Withdrawing Team <br>";


        return html;
    }

    public  String Template102(String keyword1, String keyword2,String infrigement, String ROName,
                               String ROEmail, String StoreManagerName, String StoreName,String ASIN,
                               String title, String AmazonEmail, String SellerEmail, String accountName
    ) {
        String html = "<br>Dear CS,<br>" +
                "Thanks for letting us know. Please delete all the ASINs ("+ASIN+") related and reply Amazon "+
                "in this way:<br>"+
                "======================== <br><br>"+
                "Hello Seller Performance Team,<br><br>"+
                "Thank you for your email. I have deleted the relating listings immediately after read your email. <br><br>"+
                "I do not have any relating products in stock beforehand since I do Amazon resell. Whenever I sell a  "+
                "product, I will order it from here: http://www.amazon.com <br><br>"+
                "I haven't made any sales of this product. So I don't have any supplier and buyer information, like invoice "+
                "date, and item(s) descriptions and quantities <br><br>"+
                "However, if we need to contact any specific rights owner for this issue, please feel free to let me know the "+
                "contact information, I will do whatever I can to resolve the case. <br><br>"+
                "I look forward to getting your response soon. <br><br>"+
                "Best Regards, <br>"+
                "Store name: "+StoreName+"<br><br>"+
                "========================<br><br>"+
                "Please forward the email to me if the RO responds you later.  I wish you have a wonderful day!<br>"+
                "Best<br><br>"+
                "Infringement Withdrawing Team <br>";


        return html;
    }

    public  String Template103(String keyword1, String keyword2,String infrigement, String ROName,
                               String ROEmail, String StoreManagerName, String StoreName,String ASIN,
                               String title, String AmazonEmail, String SellerEmail, String accountName
    ) {
        String html = "<br>Dear CS,<br>" +
                "Please delete the following Asin and Brand relating products (If yours are European account, please check "+
                "and delete it from each country inventory.), then write an email to reply to RO.<br><br>"+
                "ASIN: " + ASIN + " <br>" +
                "Brand: " + title + " <br><br>" +
                "Best<br>"+
                "Infringement Withdrawing Team <br>";

        return html;
    }

    public String Template104(String keyword1, String keyword2,String infrigement, String ROName,
                              String ROEmail, String StoreManagerName, String StoreName,String ASIN,
                              String title, String AmazonEmail, String SellerEmail, String accountName){
        String html = "<br>Dear CS, <br><br>" +
                "Please firstly delete this brand: ("+keyword1+"). <br>"+
                "And record the case:<br>" +
                infrigement + "<br>"+
                "and do not reply to right owner <br>" +
                "Because we had sent similar email to the same right owner many times recently. We need to know the " +
                "attitude for this right owner in other accounts.<br>" +
                "<br>" +
                "Sincerely<br>" +
                "Infringement withdraw team<br>";
        return html;
    }
}
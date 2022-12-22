# Leadership Principles

## Customer Obession:
- Tell me about a time when you have to deal with difficult customer?
    Context:
        - My marketing team wanted us to generate a payment receipt for all payments. So they were supposed to provide the receipt template.They were very unresponsive for providing the exact the template, but they have provided what all to be included in the payment receipt.
        - So I used mail merge feature where in a docx with dynamic placeholders can be filled in with actual data and generate document. Me and my manager create a basic template and placed the template in our can CDN (Content delivery network). And generated payment receipt with the current user data. That way when the marketing team came with the template I can just replace the template in cdn 
- Tell bout a situation where you negotiated win-win
    - Making both discoverable and broadcast chats endable
    - Denying to remove renter account creation 
    - Taking care of abandoned renters by providing the landlord phone number or pairing them with a agent

## ownership
Tough situation where you are to step in as a leader
    - PRE regression
    - Location theme not updating in receiver side
    - One call API
    - brown person emoji in data source
    - search terms insufficient 
Tough Decision you made in a project
    - Stop working on entdB migration and move on to Location Theme

## Invent And Simplify
Tell me about time whne you solve a complex problem
- checksum to the password for automatic generated accounts and trigger reset your password email
- emoji skin tones 
    - variation-selector emojis need to replace the (variation selector with skin tone)
    - couples emoji with two base emojis
    - single base emoji, 
- Desktop app showing location emoji
    - Desktop messenger and browser messages was getting location theme, which is not meant to happen
    - lodability gk controls if theme can be loaded or not, for eg: if a theme is updated by a person who has access to theme in a group where atleast one participant is in the control group (to whome theme is not available), then loading theme is denied
    - In this case of desktop app, we want the theme to be loaded to the group even when one participant is using desktop app as long as the persons is not in control group
    - So the bug happened because, loadabiliy GK was not restrcting the theme loading and desktop app was getting this location theme which was not optimized fro desktop apps
    - What we want- we want the Desktop app to fall back to default burple theme, when location theme is set
    - To do that at the code where mutations are executed, we used new GK called allow_mutation_gks which gates depending on the type of device
    - so mutation happens for only ios and android devices and not desktop devices
    - Instead of repurposign this loadability gk we added new set of gks called allow_mutation_gks 
    - allow_mutation_gks have same config as loadability_gks except for themes which are device dependent. 

## Strong Judgement And Good Instincts
- Tell me about a time, you had to make decisions without much customer data
    - Search terms for opted out summary report
- Tell me about a time, you have to convince team members you have to propose
    # Apply now failure alert system
        - Email notificaiton is sent to marketing team, when a apply now appication is failed
        - Email notification will have details on why application is failed, usually due to incomplete data of landlords,
        - This in complete data comes from third party source like homefinder and mhoes.com, which we cannot validate
        - We did vet the data coming from home finder and homes.com, before creating applications and reduced failed applications
    # Giving renter the option of contacting the landlord when they don not get response on their application after some time

## Learn and be curious
- Tell me about a time, when you built out a processs
    # Elastic search
    - Many duplicate listings are created. Different renters clicking on same listings, creates new listing .This is not desirable.
	- **Solution:** Inorder to find listings with duplicate addresses , we can’t use sql bcoz address search is string based “like” operator and not efficient.
	- Why I chose Elastic search?
       - I wanted a key word based search for address search because address is a unstructured content
        Elasticsearch, on the other hand, provides capability to store, index, search and analyze data in real-time which lets you extract value from the data. This data could be stored directly in Elasticsearch 	cluster or collected from various other data sources, including MongoDB.
        - ElasticSearch is keyword based search engine using Lucene indexer. Lucene indexes documents in a way that it creates inverted index for specified fields of the document. When you use ElasticSearch, you provide a query which undergoes tokenization, stemming etc. The tokenized keywords in your query are matched with the inverted index to retrieve documents where those keywords occur most frequently (Lucene is tf/idf - term frequency - inverse document frequency based search indexing). There can be more sophisticated search indexing / relevance mechanisms for improving the matching / relevance than just based on term frequency as well as for the way of processing query (tokenization / query expansion / stop words / stemming / Ngrams etc.)
- Tell me about a skill,  you have recently learned
    # Django Framework , Instagram write shim architecture
    - Django follows the MVT (Model View Template) pattern which is based on the Model View Controller architecture. It’s slightly different from the MVC pattern as it maintains its own conventions, so, the controller is handled by the framework itself. The template is a presentation layer. It is an HTML file mixed with Django Template Language (DTL). The developer provides the model, the view, and the template then maps it to a URL, and finally, Django serves it to the user.
    - LEarnt form the onboarding wiki and docuemnts

## Hire and develop the best
- Tell me about a time , you had conflict someone? how you resolve this and learn from it
    - remove authentication to renters in apply now
- Tell me about a time you had to fire someone
  - Rhiannon communication in emoji skin tones project
## Insist on highest standard
- Tell me about a time , you have to make a decision for short term sacrifices for long term gains
    - special case
- Tell me about a time ,make a decision based on data and you were ultimately wrong 
## Think Big
- Tell me about your greates success
  - theme Release System - loved by designer, made them independent
- tell me about a time you were creative
 - Renter who clicked apply now on homes.com or homefinder.com land on application which had incomplete data
 - In this case, we gave renters source to contact the landlord directly and matched them with our systems agents so its a win - win for both renter and landlors
## Bias for Action
- Tell me about a time How have you convinced others to take action
 - abandoned renter, threshold when not reached out by landlords
- Tell me about a time How have you managed risk in a project
 -A/B test ent migration for theme release
## FRugality
- Tell me about a time  you turned down more resources to complete a project
 - Emoji Skin tones project just one engineer project. Did not ask for more members because I scoped the project correctly
- Tell me about a time  you gave big results with very little budget
 - Theme Release Management
 - Theme code Automation
## Earn Trust
- Give a example resolve conflict
- earn trust of team members
    - By taking ownership of project and being available
    - FOr example resolved emoji skin bugs time to time even though wrokign on toher projects
    - Obtained good knowledge on emojis dbs in messeneger ios that I helped peers from  other team 
    - Like to updated emoji -search params
    - resolved bug of no new emojis(v14 melting face emoji) in reactions tab. Had to updated the emoji table
    - ALso suggested makin emoji- table synced weekly once rather than one in a life time
## dive Deep
- Tell me about a complex project you worked on
    - emoji
    - Theme release maangement
- Tell me about a time  you changed opinion or direction using data
    - scoped emoji skin tones project to only couple and single emojis because family emojis are not used often, user surve
## have backbone , disagree and commit
- disaagreement with manager
    - reusing same api for optouts check
    - Disagreed to reuse a existing method to check if unsubscribed. Since it needs partner param, And it calls all unwanted queries about city unsubscription and all.
- how do you manage dificult conversation
    - rhiannon not being clear. BE straight and give solid reasons 
## Deliver Results
Describe challenging project you worked on, and why was it challenging
 
Tell me about a time , when employee gave you negative feedback
- 
Tell me about a time , motivate team after demoralizing event
Refocus from messenger to IGD. Team Had to abandon features like themes , word effect
- Made sure good documentation so the features are well supported by the new team

DEscribe a time your project failed
Migration of ent DB for theme gating
You had a bug , and have you resolved
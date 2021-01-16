# LANGUAGE MEMO

### Description

The application enables the dynamic learning of sentences in a foreign language.
The algorithm determines the daily number of sentences to repeat. 
New and more difficult Sentences should be repeated every day, 
but sentences for which you are not making any mistakes will be repeated less often. 
For example, well-known phrases will be repeated once every two weeks. 
The application allows learning any language at any level of difficulty, 
due to the fact that the user creates a database of word/sentences.

>The project is an alpha (pre-beta) version – basic functionality works, but still requires a number of modifications.

At the moment, the application is for personal use, but **will be expanded soon** 
with additional options (main ideas are listed at the end of the document in To do list)

#### Creation purpose
The application has been created as I didn't find any application on the internet, 
that allows dynamic learning of sentences in a foreign language. 
The expected result is that the well-known sentences will be repeated once a week, 
but new sentences will be repeated every day. That is why  I decided to create this application, 
which enables effective learning.

#### Usage

To repeat on daily basis the sentences in a foreign language you must run the program 
and enable the frontend application (*index.html* file). Then, 
the home page displays the sentenceModel in the original language and hint.  
After entering the answer, press the button ‘Check’. If the word/sentenceModel is incorrect, 
the application returns a string with empty fields filled with overlapping valid letters. 
If it's correct, we get the next sentenceModel from the daily collection.

To add a new sentenceModel to study, use endpoint `**/api/add` on category 
*'memo-controller-imp'* of Swagger UI at `**/language-memo/swagger-ui.html`.

To display a map of difficult words/sentences, you also need to use Swagger in the 
same category at the endpoint `**/api/difficult`.


## Applied technologies and tools
Backend:
- Maven
- Spring (Boot, Data)
- REST API
- Lombok 
- Swagger UI 

Frontend:
- HTML
- Typescript



## To do list
- [x] create a simple frontend to use the application
- [x] add the endpoint to show the most difficult sentences
- [ ] add input object validation
- [ ] add the endpoint to learning new sentences, outside the daily mode
- [ ] provide the possibility of creation a new account to manage own sentences
- [ ] add Spring security (Authentication and authorization type Basic)
- [ ] extend frontend application
- [ ] create difficulties levels

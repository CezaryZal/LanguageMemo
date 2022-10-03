# LANGUAGE MEMO

### Description

The application enables the dynamic learning of sentences in a foreign language.
The algorithm determines the daily number of sentences to repeat. 
New and more difficult Sentences should be repeated every day, 
but sentences for which you are not making any mistakes will be repeated less often. 
For example, well-known phrases will be repeated once every two weeks. 
The application allows learning any language at any level of difficulty, 
due to the fact that the user creates a database of word/sentences.

>The project is a beta version – main functionality works.

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
the home page displays the sentence in the original language and hint.  
After entering the answer, press the button ‘Check’. If the word/sentence is incorrect, 
the application returns a string with empty fields filled with overlapping valid letters. 
If it's correct, we get the next sentence from the daily collection.

To add a new sentence to study, use endpoint `**/api/add` on category 
*'memo-controller-imp'* of Swagger UI at `**/language-memo/swagger-ui.html`.

To display a map of difficult words/sentences, you also need to use Swagger in the 
same category at the endpoint `**/api/difficult`.

## Business Logic
Condition of 'replayLevel':
0 – repeat from yesterday
1 – repeat from day before yesterday
2 – repeat from 3 days before
4 – repeat from 5 days before
5 – repeat from 7 days before

Change 'replayLevel' while playing only one per day.
'replayLevel' will increase after placing correct answer and low tries.

Number of tries:
start – without hints
0 – show inputSpace with sentence length
1 – show with special mark
2 – show with first letter of sentence
3 - show with all first letters of sentence
4 - show with every second letter
5 – show all correct answers
6 - show all correct answers, keeps looping step until client put correct answer


## Applied technologies and tools
Backend:
- Maven
- Spring (Boot, Data)
- Junit, Mockito
- REST API
- Lombok 
- Swagger UI 

Frontend:
- HTML
- Typescript



## To do list
- [x] create a simple frontend to use the application
- [x] add the endpoint to show the most difficult sentences
- [x] add compering module new created Sentences with existing one
- [ ] **create level-repeatDate configuration in application.yml**
- [ ] **save to store last try (before show last numberOfTries)**
- [ ] add the endpoint to learning new sentences, outside the daily mode
- [ ] provide the possibility of creation a new account to manage own sentences
- [ ] add Spring security (Authentication and authorization type Basic)
- [ ] extend frontend application
- [ ] create difficulties levels

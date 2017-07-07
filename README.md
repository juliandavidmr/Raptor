# Raptor 

Lightweight tool for scanning web sites, works as spider. 
Once executed, starts scanning pages looking for websites 
to visit, with automatic indexing.

## Features

- Search for web addresses without repetition
- Refined crawler
- Ability to create multiple processes in parallel _(threads)_
- Data indexing with mysql
- The power of java in [kotlin](https://kotlinlang.org)

## Usage

It is recommended to use [IntelliJ IDEA](https://www.jetbrains.com/idea).

````bash
git clone https://github.com/juliandavidmr/Raptor.git
#=> Open folder 'Raptor' with IntelliJ IDEA 
````

If you want to run by command line then you need to install 
the [kotlin CLI](https://kotlinlang.org/docs/tutorials/command-line.html).
> **Note:** The main file is [main.kt](https://github.com/juliandavidmr/Raptor/blob/master/src/main.kt)

### MySQL

It is necessary to have MySQL _(>=5.5)_ installed. 
Then run the [db.sql](https://github.com/juliandavidmr/Raptor/blob/master/db.sql) script.


**License MIT** 

PR Accepted
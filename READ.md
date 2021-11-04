Simple Search engine, author: Szymon Kuc\
Documentation : http://localhost:8080/swagger-ui/index.html#/search-engine-controller \
1.example Post request:\
url:http://localhost:8080/enterDocuments \
content :\
[{
"id":"document1",
"content":"the brown fox jumped over the brown dog"
},\
{
"id":"document2",
"content":"the lazy brown dog sat in the corner"
},\
{
"id":"document3",
"content":"the red fox bit the lazy dog"
}
]\
example Get Request:\
url:http://localhost:8080/fox \
response:\
[
{
"id": "document1",
"score": 0.05068313851352055
}, \
{
"id": "document3",
"score": 0.05792358687259491
}
]\
3.example Get Request:\
url:http://localhost:8080/brown \
response:\
[
{
"id": "document2",
"score": 0.05068313851352055
}, \
{
"id": "document1",
"score": 0.1013662770270411
}
]
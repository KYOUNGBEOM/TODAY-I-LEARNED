### 1. flexbox
- flexbox는 주축과 교차축으로 이루어짐.
- 주축은 flex-direction에 의해 정의하며 row, row-reverse, column, colunm-reverse 네 개의 값을 가짐.
- flex-wrap을 사용하여 항목을 여러행에 나열할 수 있음. 
- 항목이 하나의 행에 들어가지 못할 정도로 클 경우에 다른 행에 자동으로 나열됨.

- justify-content 속성은 주축을 따라 flex 항목의 행을 정렬함. 초기값은 flex-start임.
- justify-content 속성에는 flex-end, center, space-around, space-between, space-evenly등이 있음.

- align-items 속성은 교차축을 따라 flex 항목의 열을 정렬함. 
- 초기값은 stretch이며, 이외에 flex-start, flex-end, center가 있음.

- align-content 속성은 플레스의 교차축 따라 배치방식을 결정함. 플렉스 항목을 한 덩이로 뭉쳐서 정렬함. 
- align-content 속성에는 flex-start, flex-end, center, space-around, space-between, space-evenly등이 있음.

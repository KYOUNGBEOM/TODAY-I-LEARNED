### 1. flexbox

- flexbox는 주축과 교차축으로 이루어짐.

- 주축은 flex-direction에 의해 정의하며 row, row-reverse, column, colunm-reverse 네 개의 값을 가짐.

- flex-wrap을 사용하여 항목을 여러행에 나열할 수 있음. 

- flex-wrap을 사용하면 목이 하나의 행에 들어가지 못할 정도로 클 경우에 다른 행에 자동으로 나열됨.

- justify-content 속성은 주축을 따라 flex 항목의 행을 정렬함. 초기값은 flex-start임.

- justify-content 속성에는 flex-end, center, space-around, space-between, space-evenly등이 있음.

- align-items 속성은 교차축을 따라 flex 항목의 열을 정렬함. 

- align-items의 초기값은 stretch이며, 이외에 flex-start, flex-end, center가 있음.

- align-content 속성은 플레스의 교차축 따라 배치방식을 결정함. 플렉스 항목을 한 덩이로 뭉쳐서 정렬함. 

- align-content 속성에는 flex-start, flex-end, center, space-around, space-between, space-evenly등이 있음.

- flex-basis는 주축의 길이를 설정함. flex-direction 값에 따라 너비 또는 높이를 조정함.

- flex-grow는 빈 공간을 채울지 여부를 결정함. 기본값은 0이고, 채울 때 다른 아이템들의 값에 비례해서 공백을 나누어 가짐.

- flex-shrink는 전체 공간이 부족할 때, 아이템의 길이가 컨텐츠의 너비 또는 flex-basis로 설정한 값보다 작아질 수 있도록 함.

- flex-shrink의 기본값은 1이며, 증가할수록 길이가 더 줄어듬.

- flex 컨테이너의 아이템들은 order 속성을 활용하여 순서를 변경할 수 있음. 기본값은 0이고, 값이 작아질 수록 앞에 배치됨.

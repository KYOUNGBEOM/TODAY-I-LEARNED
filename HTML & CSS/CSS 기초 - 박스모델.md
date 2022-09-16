### 1. 요소의 크기

- 단위로는 %, vw, vh, vmax, vmin을 사용할 수 있다.
 
- 텍스트가 한 줄인 경우 height, line height를 똑같이 맞추면 세로방향에서 가운데로 정렬된다.

- calc 연산자를 사용하여 필요에 맞는 길이로 조절할 수 있다.

### 2. margin

- margin에는 공통, 세로 가로, 위 가로 아래, 위 오른쪽 아래 왼쪽(시계방향) 4가지 적용방법이 있다.

- margin은 위아래 블록요소의 것과 상쇄된다.

- 자식요소가 부모요소를 넘어 마진을 가지고 있으면 부모요소를 넘어 마진이 적용될 수 있다.

- margin은 margin-top, bottom, left, right를 활용하여 한 쪽 방향에만 적용시킬 수 있다.

- margin에서 0 auto 값을 주면 왼쪽과 오른쪽을 동일하게 채워 요소를 중간에 위치시킨다.

- margin: 0 auto;가 안되면 body{text-align: center;}를 통해 요소를 중간에 위치시킬 수 있다.

### 3. border

-border 속성은 요소에 테두리를 줄 수 있다.

- 굵기, 스타일, 색 순으로 작성한다.(ex. border: 1px bold black;)

- content-box 일 때는 padding 안 요소에만 크기가 적용된다.

- border-box 일 때는 border-box를 포함한 border-box 안의 모든 요소를 포함하여 크기가 적용된다.

- box-sizing: border-box(or content-box);를 활용하여 적용할 수 있다.

- border-radius 속성은 모서리를 둥글게 하는데 사용된다.

- border-radius도 top, bottom, left, right 를 활용하여 특정 방향에만 적용시킬 수 있다.

### 4. overflow

- overflow 속성은 부모 요소보다 높이나 너비값이 큰 자식 요소를 나타낼 때 사용한다.

- overflow-x, overflow-y를 활용하여 따로 설정할 수 있다.

- auto, visible, hidden, scroll, scroll hidden, hidden scroll, visible hidden, hidden visible

### 5. box-shadow

- box-shadow 속성은 박스 요소에 그림자를 줄 수 있다.

- inset여부, offset-x, offset-y, blur-radius, spread-radius, color 의 자리값을 가진다.

- box-shadow 사이트를 활용하여 원하는 코드를 찾을 수 있다.

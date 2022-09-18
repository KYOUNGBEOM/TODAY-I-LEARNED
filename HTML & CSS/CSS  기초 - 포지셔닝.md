### 1. postion

- position 속성은 요소들이 화면에 어떻게 배치될지를 결정, 자식 요소에게 대물림 되지 않음.

- static은 기본 값으로 top, bottom, left, right, z-index 속성의 영향을 받지 않음.

- relative는 원래 위치를 기준으로 top~right 속성값이 적용, 요소의 위치는 이동하지만 차지하는 공백은 유지

- absolute는 static이 아닌 첫 부모 요소를 기준으로 top~right를 사용하여 위치 조절, 문서의 흐름에서 벗어나 자리를 차지하지 않음.

- fixed는 부모 요소가 아닌 viewport를 기준으로 위치를 지정, 스크롤에 영향을 받지 않음.

- sticky는 요소가 스크롤로 이동할 수 있는 공간을 top~right로 제한시킴. 

- 만약 페이지의 처음부터 끝까지 sticky를 적용시키고 싶다면 가장 바깥쪽에 코드를 작성해야한다.

### 2. z-index

- z-index는 static이 아닌 요소들 간 위아래 배치 순서를 결정함.

- auto는 0과 같으며, 같은 값의 요소들 중에는 나중에 배치된 것이 위로 올라옴.

<h2>기본 그룹과 선택자</h2>

### 1. * {
### color: black;
### }
- 모든 요소를 선택한다.
만약 같은 선택자를 사용하는 경우에는 뒤에 오는 것이 우선순위가 더 높다.


### 2. p {
### color: black;
### }
- 태그를 선택한다.


### 3..blue {
### color: black;
### }
- class선택자로서 class="blue"를 선택한다.
태그보다 우선순위가 더 높고, 페이지 상에서 여러 요소가 같은 Class를 가질 수 있다.


### 4. p.blue {
### color: black;
### }
- p태그이면서 동시에 class인 요소를 선택한다.
선택자는 구체적일수록 우선순위가 더 높다.
예를 들어 .blue<p.blue<p.blue.dark 로 순으로 우선순위가 더 높다.


### 5. #blue {
### color: black;
### }
- id 선택자이다.
class보다 우선순위가 더 높고, id는 페이지상에서 요소마다 고유해야 한다.


### 6. p, .blue, #red{
### text-decoration: none;
### }
- 그룹 선택자로써 한 번에 여러 요소를 선택할 수 있다.

<h2>결합자와 가상 클래스</h2>

### 1. .list li {
### color: black;
### }
- 자손결합자로서 class="list'로 정의된 목록 태그 안의 모든 li를 선택한다.


### 2. .list > li {
### color: black;
### }
- 자식결합자로서 class="list'로 정의된 목록 태그 안의 1촌 자손결합자 li를 선택한다.


### 3. .list> li li {
### text-decoration: underline;
### }
- 자식결합자로서 class="list'로 정의된 목록 태그 안의 모든 2촌 자손결합자 li를 선택한다.


### 4. .list ~ li {
### font-weight: bold;
### }
- class="list'로 정의된 목록 태그 안의 뒤 따르는 모든 동생결합자들을 선택한다.


### 5. list + li {
### font-style: italic;
### }
- class="list'로 정의된 목록 태그 안의 바로 다음 동생결합자를 선택한다.


### 6. ul li:first-child
   ### ul li:last-chid {
   ### color: blue;
   ### }
-첫 번째와 마지막 요소 가상 클래스를 선택한다.


### 7. .list > li:not(:first-child) {
### text-decoraiton: underline;
### }
- class="list"인 목록 태그 안의 li 중에서 첫 번째 요소 가상 클래스를 제외한 나머지 요소들을 선택한다.

### ul:not(.list) li {
### font-weight: bold;
### }
- class="list"인 ul목록 태그를 제외한 나머지 ul태그 안의 li 태그를 선택한다.


### 8. ul li:nth-child(n) {
### color: blue;
### font-stlye: italic;
### }
- ~ 번째 요소 가상 클래스로 #, n, n+#, odd, even 등 적용가능


### 9. li:hover {
### font-weight: bold;
### }
- 마우스 오버 가상 클래스로써 마우스를 요소에 가져다대면 설정한 값에 맞춰 변한다.

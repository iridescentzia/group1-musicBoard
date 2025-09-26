# 🎵 MusicBoard

**MusicBoard**는 음악 정보를 카드형 보드와 상세 페이지로 관리할 수 있는 웹 애플리케이션입니다.  
목록/상세 조회부터 등록·수정·삭제( CRUD )까지 한 흐름으로 제공하며, 검색·정렬과 라우팅, 상태관리를 통해 사용성이 높은 보드형 UI를 지향합니다.

---

## 🚩 프로젝트 개요
- **목표**: 음악(제목/아티스트/장르/발매일/커버 이미지 등)의 CRUD를 직관적 보드 UI로 제공
- **핵심 기능**: 목록/상세, 등록, 수정, 삭제, 검색/정렬, 라우팅, 상태관리
- **구성**: Frontend(Vue 3) + Backend(Spring Legacy + MyBatis)

---

## 🛠 기술 스택

### 💻 Frontend
<p>
  <img src="https://img.shields.io/badge/Vue.js-42b883?style=flat-square&logo=vuedotjs&logoColor=white" />
  <img src="https://img.shields.io/badge/Vue%20Router-4FC08D?style=flat-square&logo=vuedotjs&logoColor=white" />
  <img src="https://img.shields.io/badge/Store(Pinia/Vuex)-35495E?style=flat-square&logo=vuedotjs&logoColor=white" />
  <img src="https://img.shields.io/badge/CSS3-1572B6?style=flat-square&logo=css3&logoColor=white" />
</p>

### 🖥 Backend
<p>
  <img src="https://img.shields.io/badge/Java-007396?style=flat-square&logo=openjdk&logoColor=white" />
  <img src="https://img.shields.io/badge/Spring%20Legacy-6DB33F?style=flat-square&logo=spring&logoColor=white" />
  <img src="https://img.shields.io/badge/MyBatis-1F77B4?style=flat-square&logo=databricks&logoColor=white" />
</p>

### 🤝 Collaboration
<p>
  <img src="https://img.shields.io/badge/GitHub-181717?style=flat-square&logo=github&logoColor=white" />
  <img src="https://img.shields.io/badge/Notion-000000?style=flat-square&logo=notion&logoColor=white" />
  <img src="https://img.shields.io/badge/Discord-5865F2?style=flat-square&logo=discord&logoColor=white" />
</p>

---

## 👥 팀원

<table>
  <tr>
    <td align="center">
      <img src="https://github.com/subeen1902.png" height="120" alt="주수빈"/>
      <br /><a href="https://github.com/subeen1902"><b>주수빈</b></a>
      <ul align="left">
        <li>풀스택</li>
      </ul>
    </td>
    <td align="center">
      <img src="https://github.com/iridescentzia.png" height="120" alt="이지아"/>
      <br /><a href="https://github.com/iridescentzia"><b>이지아</b></a>
      <ul align="left">
        <li>풀스택</li>
      </ul>
    </td>
    <td align="center">
      <img src="https://github.com/yel0732.png" height="120" alt="이은솔"/>
      <br /><a href="https://github.com/yel0732"><b>이은솔</b></a>
      <ul align="left">
        <li>풀스택</li>
      </ul>
    </td>
    <td align="center">
      <img src="https://github.com/kwon990217.png" height="120" alt="권혁주"/>
      <br /><a href="https://github.com/kwon990217"><b>권혁주</b></a>
      <ul align="left">
        <li>풀스택</li>
      </ul>
    </td>
  </tr>
</table>

---

## ✨ 주요 기능 (Features)

- **카드형 보드 UI**: 썸네일(커버), 제목, 아티스트, 장르, 발매일 요약 표시
- **상세 페이지**: 전체 정보 조회 + 이전/다음 이동
- **등록/수정/삭제(CRUD)**: 필수 항목 검증, 성공/실패 토스트 피드백
- **검색/정렬**: 제목/아티스트/장르 기준 검색, 최신/오래된순 등 정렬
- **라우팅/상태관리**: Vue Router로 페이지 전환, Store로 공용 상태 관리
- **백엔드 연동**: Spring Legacy + MyBatis 기반 REST API 호출

---

## 📝 커밋 컨벤션
| 태그       | 의미                                |
|------------|-------------------------------------|
| `feat`     | 새로운 기능 추가                     |
| `fix`      | 버그 수정                            |
| `docs`     | 문서 수정(README, 주석 등)          |
| `style`    | 스타일 변경(포맷팅, 세미콜론 등)    |
| `refactor` | 리팩토링(기능 변경 없음)             |
| `test`     | 테스트 추가/수정                     |
| `chore`    | 빌드/환경설정/기타 변경              |

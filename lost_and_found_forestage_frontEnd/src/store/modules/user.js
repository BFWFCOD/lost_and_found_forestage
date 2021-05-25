export default {
  namespaced: true,
  state: {
    id: 0,
    studentNo: 0,
    name: '', 
    key: '',    // 弃用
    loginButtonVisible: true, // 弃用
  },
  getters: {
    userid: state => {
      return state.id
    }
  },
  mutations: {
    updateId(state, id) {
      state.id = id
    },
    updateName(state, name) {
      state.name = name
    },
    updateKey(state, key) {  // 弃用
      state.key = key
    },
    updateLoginButtonVisible(state, key) {  // 弃用
      state.loginButtonVisible = key
    },
    updateStudentNo(state, key) {
      state.studentNo = key
    }
  }
}

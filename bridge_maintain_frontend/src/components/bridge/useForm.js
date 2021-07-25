import { ElMessage } from "element-plus"
import { ref, reactive } from "vue"
import axios from "../../api/request.js"

const dialogVisible = ref(false)

const bridgeTypes = ref([])

const bridgeForm = reactive({
  bridgeId: 0,
  bridgeName: "",
  roadName: "",
  manageUnit: "",
  maintainUnit: "",
  watchUnit: "",
  buildUnit: "",
  buildYear: "",
  maintainType: "",
  maintainClass: "",
  load: "",
  length: "",
  width: "",
  spanNum: "",
  pierNum: "",
  abutmentNum: "",
  roadWidth: "",
  sideWalkWidth: "",
  maxWaterline: "",
  bridgeType: {
    typeId: undefined,
  },
})

const rules = {
  bridgeName: [{ required: true, message: "桥梁名不能为空", trigger: "blur" }],
  roadName: [{ required: true, message: "桥梁名不能为空", trigger: "blur" }],
  bridgeType: [
    { required: true, message: "桥梁类型不能为空", trigger: "blur" },
  ],
  manageUnit: [
    { required: true, message: "管理单位不能为空", trigger: "blur" },
  ],
  maintainUnit: [
    { required: true, message: "养护单位不能为空", trigger: "blur" },
  ],
  watchUnit: [{ required: true, message: "监管单位不能为空", trigger: "blur" }],
  buildYear: [{ required: true, message: "建成年月不能为空", trigger: "blur" }],
  maintainType: [
    { required: true, message: "养护类型不能为空", trigger: "blur" },
  ],
  maintainClass: [
    { required: true, message: "养护等级不能为空", trigger: "blur" },
  ],
  load: [{ required: true, message: "设计载荷不能为空", trigger: "blur" }],
  length: [{ required: true, message: "桥梁总长不能为空", trigger: "blur" }],
  width: [{ required: true, message: "桥梁总宽不能为空", trigger: "blur" }],
  spanNum: [{ required: true, message: "桥梁跨数不能为空", trigger: "blur" }],
  pierNum: [{ required: true, message: "桥梁台数不能为空", trigger: "blur" }],
  abutmentNum: [
    { required: true, message: "桥梁墩数不能为空", trigger: "blur" },
  ],
  roadWidth: [
    { required: true, message: "车行道净宽不能为空", trigger: "blur" },
  ],
  sideWalkWidth: [
    { required: true, message: "人行道净宽为空", trigger: "blur" },
  ],
  maxWaterline: [
    { required: true, message: "最高水位不能为空", trigger: "blur" },
  ],
}
const formRef = ref(null)

export default function useBridgeForm() {
  const bridgeTypeFocus = async () => {
    const result = await axios.get("/bridgeType")
    bridgeTypes.value = result
  }

  const handleEditBridge = (info) => {
    bridgeForm.bridgeId = info.bridgeId
    bridgeForm.bridgeName = info.bridgeName
    bridgeForm.roadName = info.roadName
    bridgeForm.manageUnit = info.manageUnit
    bridgeForm.maintainUnit = info.maintainUnit
    bridgeForm.watchUnit = info.watchUnit
    bridgeForm.buildUnit = info.buildUnit
    bridgeForm.buildYear = info.buildYear
    bridgeForm.maintainType = info.maintainType
    bridgeForm.maintainClass = info.maintainClass
    bridgeForm.load = info.load
    bridgeForm.length = info.length
    bridgeForm.width = info.width
    bridgeForm.spanNum = info.spanNum
    bridgeForm.pierNum = info.pierNum
    bridgeForm.abutmentNum = info.abutmentNum
    bridgeForm.roadWidth = info.roadWidth
    bridgeForm.sideWalkWidth = info.sideWalkWidth
    bridgeForm.maxWaterline = info.maxWaterline
    bridgeForm.bridgeType.typeId = info.bridgeType.typeId
    dialogVisible.value = true
    bridgeTypeFocus()
  }

  const setFormRef = (form) => {
    formRef.value = form
  }

  const handleDialogClose = () => {
    for (let key in bridgeForm.value) {
      bridgeForm[key] = undefined
    }
  }

  const resetForm = () => {
    formRef.value.resetFields()
  }

  const submitForm = async () => {
    formRef.value.validate(async (valid) => {
      if (!valid) ElMessage.error("表单验证失败!")
      axios.post("/bridge", bridgeForm).then(() => {
        for (let key in bridgeForm.value) {
          bridgeForm[key] = undefined
        }
        dialogVisible.value = false
      })
    })
  }

  return {
    dialogVisible,
    bridgeForm,
    bridgeTypes,
    bridgeTypeFocus,
    rules,
    formRef,
    setFormRef,
    handleDialogClose,
    resetForm,
    submitForm,
    handleEditBridge,
  }
}

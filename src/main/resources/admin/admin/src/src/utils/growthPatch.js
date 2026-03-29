export function pickList(payload) {
  if (!payload) return []
  if (Array.isArray(payload)) return payload
  if (Array.isArray(payload.list)) return payload.list
  if (payload.data && Array.isArray(payload.data.list)) return payload.data.list
  if (payload.page && Array.isArray(payload.page.list)) return payload.page.list
  if (payload.data && payload.data.page && Array.isArray(payload.data.page.list)) return payload.data.page.list
  return []
}

export function pickTotal(payload) {
  if (!payload) return 0
  return payload.total || (payload.data && payload.data.total) || (payload.page && payload.page.totalCount) || (payload.data && payload.data.page && payload.data.page.totalCount) || 0
}

export function pickData(payload) {
  return (payload && (payload.data || payload)) || {}
}

export function okMessage(vm, res, fallback) {
  const msg = (res && res.data && (res.data.msg || res.data.message)) || fallback || '操作成功'
  vm.$message.success(msg)
}

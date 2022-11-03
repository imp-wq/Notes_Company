/*
 * @Author: wuzhenyang
 * @Date: 2022-11-02 16:16:56
 * @LastEditors: wuzhenyang
 * @LastEditTime: 2022-11-02 16:17:02
 * @FilePath: \tsnode-tools\src\db-tool\main.ts
 * @Description:
 */
import fs from 'fs'

const files = fs.readFileSync('./source.txt', 'utf-8')

const rows = files.split('\r\n')

const result = rows.map(item => {
  const [key, label] = item.split('\t')
  return { prop: key, label }
})

const resStr = `[${result.map(item => JSON.stringify(item)).join()}]`
fs.writeFileSync('./map_array.js', resStr)
console.log(result)

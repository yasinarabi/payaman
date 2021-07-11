package ir.yasinarabi.smartsms.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import ir.yasinarabi.smartsms.R
import ir.yasinarabi.smartsms.models.Bank

class BankAdapter(val context: Context, val banks: Array<Bank>): BaseAdapter() {
    private var layoutInflater: LayoutInflater? = null
    private lateinit var imageView: ImageView
    private lateinit var textView: TextView

    override fun getCount(): Int {
        return banks.size
    }

    override fun getItem(position: Int): Any {
        return banks[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        if (layoutInflater == null) {
            layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }
        if (convertView == null) {
            convertView = layoutInflater!!.inflate(R.layout.item_bank, null)
        }
        imageView = convertView!!.findViewById(R.id.item_imgv)
        textView = convertView.findViewById(R.id.item_text)
        imageView.setImageResource(banks[position].image)
        textView.text = banks[position].name
        return convertView
    }
}
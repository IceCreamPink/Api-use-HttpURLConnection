    /*
    package com.example.testingapi3

    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import android.widget.Button
    import android.widget.TextView
    import androidx.recyclerview.widget.RecyclerView
    import java.io.InputStreamReader
    import java.net.HttpURLConnection
    import java.net.URL

    class Adapter(var mList: MutableList<Request>) : RecyclerView.Adapter<Adapter.AdapterViewHolder>() {
        companion object{
            private const val BASE_URL ="https://jsonplaceholder.typicode.com/posts"
            val connection = URL(BASE_URL).openConnection() as HttpURLConnection

        }

        inner class AdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val tvText: TextView = itemView.findViewById(R.id.tvText)

            fun bind(request: Request) {
                val text = "userId: ${request.userId}\n" +
                        "id: ${request.id}\n" +
                        "title: ${request.title}\n" +
                        "body: ${request.body}\n"
                tvText.text = text

            }
        }
         fun show():Thread{
            if (connection.responseCode == 200){
                val inputSystem = connection.inputStream
                val inputStreamReader = InputStreamReader(inputSystem, "UTF-8")
                updateUi()
                inputStreamReader.close()
                inputSystem.close()
            }else{

            }

        }

        private fun updateUi(request: Request) {

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
            return AdapterViewHolder(view)
        }

        override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
            holder.bind(mList[position])
        }

        override fun getItemCount(): Int = mList.size
    }*/
    package com.example.testingapi3

    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import android.widget.Button
    import android.widget.TextView
    import android.widget.Toast
    import androidx.recyclerview.widget.RecyclerView
    import com.example.testingapi3.databinding.ItemPostBinding
    import com.google.gson.Gson
    import kotlinx.coroutines.Dispatchers
    import kotlinx.coroutines.GlobalScope
    import kotlinx.coroutines.launch
    import java.io.InputStreamReader
    import java.net.HttpURLConnection
    import java.net.URL

    class Adapter(private var mList: MutableList<Request>) : RecyclerView.Adapter<Adapter.AdapterViewHolder>() {

        inner class AdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val binding = ItemPostBinding.bind(itemView)
            private val btndell:Button = itemView.findViewById(R.id.btndell)
            private val btnupdate:Button = itemView.findViewById(R.id.btnupdate)

            fun bind(request: Request) {
                with(binding) {
                    tvText.text = "userId: ${request.userId}\n" +
                            "id: ${request.id}\n" +
                            "title: ${request.title}\n" +
                            "body: ${request.body}\n"
                btndell.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        deletedata(position)
                        Toast.makeText(itemView.context, "Data berhasil dihapus", Toast.LENGTH_SHORT).show()
                    }

                }
btnupdate.setOnClickListener {

}
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
            return AdapterViewHolder(view)
        }

        override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
            holder.bind(mList[position])
        }

        override fun getItemCount(): Int = mList.size

        fun adddata(request: Request) {
            mList.add(request)
            notifyItemInserted(mList.size - 1)
        }

        fun updatedata(position: Int, newRequest: Request) {
            mList[position] = newRequest
            notifyItemChanged(position)
        }

        fun deletedata(position: Int) {
            mList.removeAt(position)
            notifyItemRemoved(position)
        }

        fun livedata() {
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val baseUrl = "https://jsonplaceholder.typicode.com/posts"
                    val connection = URL(baseUrl).openConnection() as HttpURLConnection
                    if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                        val inputStream = connection.inputStream
                        val reader = InputStreamReader(inputStream)
                        val data = Gson().fromJson(reader, Array<Request>::class.java).toList()
                        reader.close()
                        inputStream.close()

                        // Update UI di Main Dispatcher
                        launch(Dispatchers.Main) {
                            mList.clear()
                            mList.addAll(data)
                            notifyDataSetChanged()
                        }
                    } else {
                        // Handle HTTP error
                    }
                    connection.disconnect()
                } catch (e: Exception) {
                    // Handle exception
                }
            }
        }
    }


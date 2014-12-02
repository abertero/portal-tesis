<%@tag body-content="empty" language="java" pageEncoding="UTF-8" %>
<%@attribute name="inputId" %>
<%@attribute name="callback" required="false" %>
<%@attribute name="resetOnEnter" required="false" %>
<input type="hidden" name="hidden${inputId}" id="hidden${inputId}" value=""/>
<script type="text/javascript">
  $(function () {
    var input = $("#${inputId}");
    var hiddenInput = $("#hidden${inputId}");
    var reset = "${resetOnEnter}";

    var technicians = new Bloodhound({
      name: "technicians",
      remote: "${pageContext.request.contextPath}/findTechnicianByCode?code=%QUERY",
      datumTokenizer: function (technicians) {
        return Bloodhound.tokenizers.whitespace(technicians.value);
      },
      queryTokenizer: Bloodhound.tokenizers.whitespace
    });

    technicians.initialize();

    input.typeahead({
          hint: true,
          highlight: true,
          minLength: 3
        },
        {
          name: "technicians",
          displayKey: function (technicians) {
            return technicians.code + " - " + technicians.firstName + " " + technicians.lastName;
          },
          source: technicians.ttAdapter()
        });

    input.bind("typeahead:selected", function (obj, datum, name) {
      console.log(datum.code);
      hiddenInput.val(datum.id);
      ${callback}(datum);
      if (reset == "true") {
        input.val("");
        hiddenInput.val("");
      }
    });

    $("span.twitter-typeahead").css("width", "100%");
  });
</script>